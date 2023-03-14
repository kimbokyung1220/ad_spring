package com.example.demo.service;

import com.example.demo.controller.request.ad.RegisterAdRequestDto;
import com.example.demo.entity.Ad;
import com.example.demo.entity.DadDet;
import com.example.demo.entity.DadDetBid;
import com.example.demo.entity.Kwd;
import com.example.demo.repository.DadDetBidRepository;
import com.example.demo.repository.DadDetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DadDetService {
    private final DadDetRepository dadDetRepository;
    private final DadDetBidRepository dadDetBidRepository;

    public void saveDadDet(Ad ad, Kwd kwd, RegisterAdRequestDto adRequestDto) {
        // 직접광고 상세 등록
        DadDet dadDet = adRequestDto.createDadDet(ad, kwd);
        dadDetRepository.save(dadDet);
        
//        DadDetBidRequestDto dadDetBidRequestDto = new DadDetBidRequestDto(dadDet.getDadDetId(), adRequestDto.getBidCost());
        dadDetBidRepository.save(new DadDetBid(dadDet).addCost(dadDet.getDadDetId(), adRequestDto.getBidCost()));


    }
}
