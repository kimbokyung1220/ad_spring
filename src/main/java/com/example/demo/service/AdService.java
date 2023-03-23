package com.example.demo.service;

import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.controller.request.ad.AdUseConfigYnListRequestDto;
import com.example.demo.controller.request.ad.AdUseConfigYnRequestDto;
import com.example.demo.controller.request.ad.RegisterAdRequestDto;
import com.example.demo.controller.request.kwd.KwdRequestDto;
import com.example.demo.controller.response.AdResponseDto;
import com.example.demo.entity.*;
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
    private final KwdRepository kwdRepository;
    private final DadDetService dadDetService;

    public AdResponseDto saveAd(RegisterAdRequestDto adRequestDto, HttpServletRequest request) {
        Member member = validation.getMember(request);
        // 광고주
        Adv adv = validation.isPresentAdv(member.getMemberId());
        // 상품
        Item item = validation.isPresentItem(adRequestDto.getItemId());
        // 광고그룹
        Agroup agroup = validation.isPresentAgroup(adRequestDto.getAgroupId());
        // 광고 등록
        Ad ad = adRequestDto.createAd(adv, item, agroup);
        adRepository.save(ad);

        // 키워드 등록
        saveKwd(ad, adRequestDto);

        return null;

    }

    public void saveKwd(Ad ad, RegisterAdRequestDto adRequestDto) {
        List<KwdRequestDto> kwdList = adRequestDto.getKwds();
        if (kwdList.isEmpty()) {
            dadDetService.saveDadDet(ad, adRequestDto);
            return;
        }
        for (int i = 0; i < kwdList.size(); i++) {
            String kwdName = kwdList.get(i).getKwdName();
            if (!kwdRepository.existsByKwdName(kwdName)) {
                // 키워드 저장
                Kwd kwdInfo = Kwd.builder()
                        .kwdName(kwdList.get(i).getKwdName())
                        .sellPossKwdYn(1)
                        .manualCnrKwdYn(0)
                        .build();
                kwdRepository.save(kwdInfo);

            }
        }
        dadDetService.saveDadDet(ad, adRequestDto);
    }
    /** 광고 사용 설정 여부 변경- [광고관리] */
    @Transactional
    public Long updateAdUseConfig(AdUseConfigYnRequestDto adUseConfigYnRequestDto) {
        Ad ad = validation.isPresentAd(adUseConfigYnRequestDto.getAdId());
        ad.updateAdUseConfig(adUseConfigYnRequestDto);

        // 관련 직접광고 사용 설정 여부 변경
        if(adUseConfigYnRequestDto.getAdUseConfigYn() == 1) {
            dadDetService.updateDedUseConfig(ad, 0);
        } else {
            dadDetService.updateDedUseConfig(ad, 1);
        }
        return ad.getAgroup().getAgroupId();
    }
    /** 광고 사용 설정 여부 변경(체크박스) - [광고관리] */
    @Transactional
    public void updateAdUseConfigs(AdUseConfigYnListRequestDto requestDtos, HttpServletRequest servletRequest) {
        List<AdUseConfigYnRequestDto> adUseConfigList = requestDtos.getAdUseConfigYnList();
        if(requestDtos.getCode() == 1) {
            for (int i = 0; i < adUseConfigList.size(); i++) {
                Ad ad = validation.isPresentAd(adUseConfigList.get(i).getAdId());
                ad.updateOnAdUseConfig();
                dadDetService.updateDedUseConfig(ad, 0);
            }
        } else {
            for (int i = 0; i < adUseConfigList.size(); i++) {
                Ad ad = validation.isPresentAd(adUseConfigList.get(i).getAdId());
                ad.updateOffAdUseConfig();
                dadDetService.updateDedUseConfig(ad, 1);
            }
        }
    }
}


