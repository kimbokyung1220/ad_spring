package com.example.demo.service;

import com.example.demo.controller.request.ad.RegisterAdRequestDto;
import com.example.demo.controller.request.kwd.KwdNameRequestDto;
import com.example.demo.controller.request.kwd.KwdRequestDto;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.controller.response.dadDet.DadDetDto;
import com.example.demo.controller.response.dadDet.IspAdListResponseDto;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.common.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DadDetService {
    private final EntityManager em;
    private final DadDetBidRepository dadDetBidRepository;
    private final DadDetRepository dadDetRepository;
    private final DadDetDslRepositoryImpl dadDetDslRepository;
    private final CnrReqRepository cnrReqRepository;
    private final ValidationService validation;

    @Transactional
    public void saveDadDet(Ad ad, RegisterAdRequestDto adRequestDto) {
        List<KwdRequestDto> kwdList = adRequestDto.getKwds();

        for (int i = 0; i < kwdList.size(); i++) {

            String kwdName = kwdList.get(i).getKwdName();
            Integer bidCost = kwdList.get(i).getBidCost();
            Kwd kwd = validation.isPresentKwd(kwdName);

            if (adRequestDto.getCode() == 1) {
                // 직접광고 상세 등록 - 수동 등록
                DadDet dadDetInfo = adRequestDto.createDadDet(ad, kwd);
                DadDet dadDet = dadDetRepository.save(dadDetInfo);
                dadDetBidRepository.save(new DadDetBid(dadDet).addCost(dadDet.getDadDetId(), bidCost));
                // 검수 요청 등록 - 수동 등록
                CnrReq cnrReq = cnrReqRepository.save(new CnrReq().saveManualCnrReq(dadDet));
                cnrReqRepository.save(cnrReq);
                dadDet.update(cnrReq);
            } else {
                // 직접광고 상세 등록 - 일반 등록
                DadDet dadDetInfo = adRequestDto.createDadDet(ad, kwd);
                DadDet dadDet = dadDetRepository.save(dadDetInfo);
                dadDetBidRepository.save(new DadDetBid(dadDet).addCost(dadDet.getDadDetId(), bidCost));
                // 검수 요청 등록 - 일반 등록
                CnrReq cnrReq = cnrReqRepository.save(new CnrReq().saveCnrReq(dadDet));
                cnrReqRepository.save(cnrReq);
                dadDet.update(cnrReq);
            }
        }
    }

    @Transactional
    public void itemDedUseConfig(Ad ad, int useConfigValue) {
        List<DadDet> dadDets = dadDetRepository.findByAd(ad);
        for (int i = 0; i < dadDets.size(); i++) {
            DadDet dadDet = validation.isPresentDadDet(dadDets.get(i).getDadDetId());
            dadDet.updateItemDadUseConfig(useConfigValue);
        }
    }

    @Transactional
    public void itemDedAct(Ad ad) {
        List<DadDet> dadDets = dadDetRepository.findByAd(ad);
        for (int i = 0; i < dadDets.size(); i++) {
            DadDet dadDet = validation.isPresentDadDet(dadDets.get(i).getDadDetId());
            dadDet.updateOffDadActYn();
        }
    }

    /**
     * 광고 검수 대상 리스트 - [광고 검수]
     */
    @Transactional
    public ResponseDto<List<DadDetDto>> searchIspAdList(KwdNameRequestDto kwdNameRequestDto) {
        List<DadDetDto> IspAdList = dadDetDslRepository.searchIspAdList(kwdNameRequestDto.getKwdName());
        IspAdList.stream()
                .map(dadDetDto -> IspAdListResponseDto.IspAdList(dadDetDto))
                .collect(Collectors.toList());

        return ResponseDto.success(IspAdList);

    }
}
