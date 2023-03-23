package com.example.demo.controller;

import com.example.demo.controller.request.ad.AdUseConfigYnRequestDto;
import com.example.demo.controller.request.ad.RegisterAdRequestDto;
import com.example.demo.controller.response.AdResponseDto;
import com.example.demo.repository.AdRepository;
import com.example.demo.service.AdService;
import lombok.RequiredArgsConstructor;
import org.aspectj.runtime.internal.cflowstack.ThreadCounter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdController {
    private final AdService adService;
    private final AdRepository adRepository;


    /**
     * 광고 등록
     */
    @PostMapping("/ad")
    public ResponseEntity<AdResponseDto> saveAd(@RequestBody RegisterAdRequestDto adRequestDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(adService.saveAd(adRequestDto, request));
    }

    /** 광고 사용 설정 여부 변경 - [광고관리] */
    @PostMapping("/ad/{adId}")
    public void updateAdUseConfig(@PathVariable Long adId, AdUseConfigYnRequestDto adUseConfigYnRequestDto)  {
        adService.updateAdUseConfig(adId, adUseConfigYnRequestDto);
    }

}
