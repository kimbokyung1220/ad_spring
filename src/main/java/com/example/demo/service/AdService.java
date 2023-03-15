package com.example.demo.service;

import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.controller.request.ad.RegisterAdRequestDto;
import com.example.demo.controller.response.ad.AdResponseDto;
import com.example.demo.controller.response.agroup.AgroupResponseDto;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdService {
    private final KwdService kwdService;
    private final TokenProvider tokenProvider;
    private final AdvRepository advRepository;
    private final ItemRepository itemRepository;
    private final AgroupRepository agroupRepository;
    private final AdRepository adRepository;
    private final KwdRepository kwdRepository;
    private final DadDetService dadDetService;

    public AdResponseDto saveAd(RegisterAdRequestDto adRequestDto, HttpServletRequest request) {
        Member member = validateMember(request);
        // 광고주
        Adv adv = isPresentAdv(member.getMemberId());
        // 상품
        Item item = isPresentItem(adRequestDto.getItemId());
        // 광고그룹
        Agroup agroup = isPresentAgroup(adRequestDto.getAgroupId());
        // 광고 등록
        Ad ad = adRequestDto.createAd(adv, item, agroup);
        adRepository.save(ad);

        // 키워드 등록
        saveKwd(ad, adRequestDto);

        return null;

    }

    public void saveKwd(Ad ad, RegisterAdRequestDto adRequestDto) {
        List<Kwd> kwdList = adRequestDto.getKwds();

        if (kwdList.size() >= 0) {
            for (int i = 0; i < kwdList.size(); i++) {
                if (!kwdRepository.existsByKwdName(kwdList.get(i).getKwdName())) {
                    // 키워드 저장
                    Kwd kwdInfo = Kwd.builder()
                            .kwdName(kwdList.get(i).getKwdName())
                            .sellPossKwdYn(1)
                            .manualCnrKwdYn(0)
                            .build();
                    kwdRepository.save(kwdInfo);

                    Kwd kwd = isPresentKwd(adRequestDto.getKwdName());
                    dadDetService.saveDadDet(ad, kwd, adRequestDto);


                }
            }
        } else {
            dadDetService.saveDadDet(ad, null, adRequestDto);
        }
    }

    @Transactional(readOnly = true)
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Authorization").substring(7))) {
            return null;
        }
        //Authentication에서 멤버 객체 불러오기
        return tokenProvider.getMemberFromAuthentication();
    }

    // 광고주 확인
    @Transactional(readOnly = true)
    public Adv isPresentAdv(String advId) {
        Optional<Adv> adv = advRepository.findById(advId);
        return adv.orElse(null);
    }

    //상품 확인
    @Transactional(readOnly = true)
    public Item isPresentItem(Long itemId) {
        Optional<Item> item = itemRepository.findByItemId(itemId);
        return item.orElse(null);
    }

    // 광고그룹 확인
    @Transactional(readOnly = true)
    public Agroup isPresentAgroup(Long agroupId) {
        Optional<Agroup> agroup = agroupRepository.findById(agroupId);
        return agroup.orElse(null);
    }

    // 키워드 확인
    @Transactional(readOnly = true)
    public Kwd isPresentKwd(String kwdName) {
        Optional<Kwd> kwd = kwdRepository.findByKwdName(kwdName);
        return kwd.orElse(null);
    }
}
