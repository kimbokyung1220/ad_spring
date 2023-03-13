package com.example.demo.service;

import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.controller.request.ad.AdRequestDto;
import com.example.demo.entity.*;
import com.example.demo.repository.AdRepository;
import com.example.demo.repository.AdvRepository;
import com.example.demo.repository.AgroupRepository;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdService {
    private final TokenProvider tokenProvider;
    private final AdvRepository advRepository;
    private final ItemRepository itemRepository;
    private final AgroupRepository agroupRepository;
    private final AdRepository adRepository;

    public void saveAd(AdRequestDto adRequestDto, HttpServletRequest request) {
        Member member = validateMember(request);
        // 광고주
        Adv adv = isPresentAdv(member.getMemberId());
        // 상품
        Item item = isPresentItem(adRequestDto.getItemId());
        // 광고그룹
        Agroup agroup = isPresentAgroup(adRequestDto.getAgroupId());

        Ad ad = adRequestDto.createAd(adv, item, agroup);
        adRepository.save(ad);
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
    // 상품 확인
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
}
