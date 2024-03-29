package com.example.demo.service.common;

import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.DadDetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ValidationService {
    private final TokenProvider tokenProvider;
    private final AdRepository adRepository;
    private final AdvRepository advRepository;
    private final ItemRepository itemRepository;
    private final AgroupRepository agroupRepository;
    private final KwdRepository kwdRepository;
    private final DadDetRepository dadDetRepository;
    private final CnrReqRepository cnrReqRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member getMember(HttpServletRequest request) {
        if (tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        //Authentication에서 멤버 객체 불러오기
        return tokenProvider.getMemberFromAuthentication();
    }
    // 광고 확인
    public Ad isPresentAd(Long adId) {
        Optional<Ad> ad = adRepository.findById(adId);
        return ad.orElse(null);
    }

    // 광고주 확인
    public Adv isPresentAdv(String advId) {
        Optional<Adv> adv = advRepository.findById(advId);
        return adv.orElse(null);
    }

    public Member isPresentMember(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        return member.orElse(null);
    }

    //상품 확인
    public Item isPresentItem(Long itemId) {
        Optional<Item> item = itemRepository.findByItemId(itemId);
        return item.orElse(null);
    }

    // 광고그룹 확인
    public Agroup isPresentAgroup(Long agroupId) {
        Optional<Agroup> agroup = agroupRepository.findById(agroupId);
        return agroup.orElse(null);
    }

    // 키워드 확인
    public Kwd isPresentKwd(String kwdName) {
        Optional<Kwd> kwd = kwdRepository.findByKwdName(kwdName);
        return kwd.orElse(null);
    }

    // 키워드
    public Kwd isPresentKwdId(Long kwdId) {
        Optional<Kwd> kwd = kwdRepository.findByKwdId(kwdId);
        return kwd.orElse(null);
    }
    //직접광고 상세
    public DadDet isPresentDadDet(Long dadDetId) {
        Optional<DadDet> dadDet = dadDetRepository.findByDadDetId(dadDetId);
        return dadDet.orElse(null);
    }
    public CnrReq isPresentCnrReq(Long cnrReqId) {
        Optional<CnrReq> CnrReq = cnrReqRepository.findByCnrReqId(cnrReqId);
        return CnrReq.orElse(null);
    }
}
