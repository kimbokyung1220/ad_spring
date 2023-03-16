package com.example.demo.service;

import com.example.demo.controller.request.ad.RegisterAdRequestDto;
import com.example.demo.controller.request.kwd.KwdRequestDto;
import com.example.demo.entity.*;
import com.example.demo.repository.CnrReqRepository;
import com.example.demo.repository.DadDetBidRepository;
import com.example.demo.repository.DadDetRepository;
import com.example.demo.repository.KwdRepository;
import com.example.demo.service.common.DadDetBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DadDetService {
    private final EntityManager em;
    private final DadDetRepository dadDetRepository;
    private final DadDetBidRepository dadDetBidRepository;
    private final CnrReqRepository cnrReqRepository;

    private final KwdRepository kwdRepository;

    @Transactional
    public void saveDadDet(Ad ad, RegisterAdRequestDto adRequestDto) {
        List<KwdRequestDto> kwdList = adRequestDto.getKwds();
        CnrReq cnrReq = new CnrReq();
        for (int i = 0; i < kwdList.size(); i++) {
            String kwdName = kwdList.get(i).getKwdName();
            Integer bidCost = kwdList.get(i).getBidCost();
            Kwd kwd = isPresentKwd(kwdName);

            // 직접광고 상세 등록
            DadDet dadDet = adRequestDto.createDadDet(ad, kwd);
//            dadDet.addCnrReq(cnrReq);
//            dadDetRepository.save(dadDet);
            em.persist(dadDet);
            dadDetBidRepository.save(new DadDetBid(dadDet).addCost(dadDet.getDadDetId(), bidCost));
            cnrReq.saveCnrReq(dadDet);
//            cnrReqRepository.save(new CnrReq().saveCnrReq(dadDet));
        }


//        DadDetBidRequestDto dadDetBidRequestDto = new DadDetBidRequestDto(dadDet.getDadDetId(), adRequestDto.getBidCost());
//        dadDetBidRepository.save(new DadDetBid(dadDet).addCost(dadDet.getDadDetId(), adRequestDto.getBidCost()));
    }

    // 키워드 확인
    @Transactional(readOnly = true)
    public Kwd isPresentKwd(String kwdName) {
        Optional<Kwd> kwd = kwdRepository.findByKwdName(kwdName);
        return kwd.orElse(null);
    }
}
