package com.example.demo.service;

import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.controller.request.ad.*;
import com.example.demo.controller.request.kwd.KwdRequestDto;
import com.example.demo.controller.response.AdResponseDto;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.entity.*;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.*;
import com.example.demo.service.common.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdService {
    private final ValidationService validation;
    private final AdRepository adRepository;
    private final AgroupRepository agroupRepository;
    private final KwdRepository kwdRepository;
    private final DadDetService dadDetService;
    private final AdvRepository advRepository;

    /**
     * 하나의 상품은 하나의 광고만 등록 가능
     */
    public ResponseDto<Long> checkResAdItem(Long itemId) {
        Item item = validation.isPresentItem(itemId);
        if (adRepository.existsAdByItemAndAdActYnLike(item, 1)) {
            return ResponseDto.fail(ErrorCode.EXIST_AD_ITEM.getCode(), ErrorCode.EXIST_AD_ITEM.getMessage());
        }
        return ResponseDto.success(itemId);
    }

    public ResponseDto<String> saveAd(RegisterAdRequestDto adRequestDto, HttpServletRequest request) {

        Member member = validation.getMember(request);
        // 광고주
        Adv adv = validation.isPresentAdv(member.getMemberId());
        // 상품
        Item item = validation.isPresentItem(adRequestDto.getItemId());

        if (adRepository.existsAdByItemAndAdActYnLike(item, 1)) {
            return ResponseDto.fail(ErrorCode.EXIST_AD.getCode(), ErrorCode.EXIST_AD.getMessage());
        }

        // 광고그룹
        Agroup agroup = agroupRepository.findByAgroupName(adRequestDto.getAgroupName());
        // 광고 등록
        Ad ad = adRequestDto.createAd(adv, item, agroup);
        adRepository.save(ad);
        // 키워드 등록
        saveKwd(ad, adRequestDto);

        return ResponseDto.success("광고 등록 완료! :-)");

    }

    public void saveKwd(Ad ad, RegisterAdRequestDto adRequestDto) {
        List<KwdRequestDto> kwdList = adRequestDto.getKwds();
        if (kwdList.isEmpty()) {
            dadDetService.saveDadDet(ad, adRequestDto);
            return;
        }

        for (int i = 0; i < kwdList.size(); i++) {
            String kwdName = kwdList.get(i).getKwdName();
            // 키워드 신규등록 (일반등록)
            if (!kwdRepository.existsByKwdName(kwdName)) {
                Kwd kwdInfo = adRequestDto.createKwd(kwdList.get(i).getKwdName());
                kwdRepository.save(kwdInfo);

            } else {
                Kwd kwd = validation.isPresentKwd(kwdName);
                // 판매가능여부가 false인 키워드
                if (kwd.getSellPossKwdYn() == 0) {
                    throw new CustomException(ErrorCode.KWD_UNABLE_SELL);
                }
            }
        }
        dadDetService.saveDadDet(ad, adRequestDto);
    }

    /**
     * 광고 사용 설정 여부 변경- [광고관리]
     */
    @Transactional
    public Long updateAdUseConfig(AdUseConfigYnRequestDto adUseConfigYnRequestDto) {
        Ad ad = validation.isPresentAd(adUseConfigYnRequestDto.getAdId());
        ad.updateAdUseConfig(adUseConfigYnRequestDto);

        // 관련 직접광고 사용 설정 여부 변경
        if (adUseConfigYnRequestDto.getAdUseConfigYn() == 1) {
            dadDetService.itemDedUseConfig(ad, 1);
        } else {
            dadDetService.itemDedUseConfig(ad, 0);
        }
        return ad.getAgroup().getAgroupId();
    }

    /**
     * 광고 사용 설정 여부 변경(체크박스) - [광고관리]
     */
    @Transactional
    public void updateAdUseConfigs(AdUseConfigYnListRequestDto requestDtos, HttpServletRequest servletRequest) {
        List<AdUseConfigYnRequestDto> adUseConfigList = requestDtos.getAdUseConfigYnList();
        if (requestDtos.getCode() == 1) {
            for (int i = 0; i < adUseConfigList.size(); i++) {
                Ad ad = validation.isPresentAd(adUseConfigList.get(i).getAdId());
                ad.updateOnAdUseConfig();
                dadDetService.itemDedUseConfig(ad, 1);
            }
        } else {
            for (int i = 0; i < adUseConfigList.size(); i++) {
                Ad ad = validation.isPresentAd(adUseConfigList.get(i).getAdId());
                ad.updateOffAdUseConfig();
                dadDetService.itemDedUseConfig(ad, 0);
            }
        }
    }

    @Transactional
    public void updateAdActYns(DeleteAdListRequestDto requestDtos, HttpServletRequest servletRequest) {
        List<DeleteAdRequestDto> deleteAdList = requestDtos.getDeleteAdList();
        for (int i = 0; i < deleteAdList.size(); i++) {
            Ad ad = validation.isPresentAd(deleteAdList.get(i).getAdId());
            ad.updateOffAdActYn();
            ad.updateOffAdUseConfig();
            dadDetService.itemDedUseConfig(ad, 0);
            dadDetService.itemDedAct(ad);
        }

    }


}


