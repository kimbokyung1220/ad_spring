package com.example.demo.controller;

import com.example.demo.controller.request.ad.AdUseConfigYnListRequestDto;
import com.example.demo.controller.request.ad.AdUseConfigYnRequestDto;
import com.example.demo.controller.request.ad.DeleteAdListRequestDto;
import com.example.demo.controller.request.ad.RegisterAdRequestDto;
import com.example.demo.controller.request.agroup.DeleteAgroupListRequestDto;
import com.example.demo.controller.response.AdResponseDto;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.repository.AdRepository;
import com.example.demo.service.AdService;
import lombok.RequiredArgsConstructor;
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
     * 하나의 상품은 하나의 광고만 등록 가능
     */
    @PostMapping("ad/item/{itemId}")
    public ResponseDto<Long> checkResAdItem(@PathVariable Long itemId) {
        return adService.checkResAdItem(itemId);
    }


    /**
     * 광고 등록
     */
    @PostMapping("/ad")
    public ResponseDto<String> saveAd(@RequestBody RegisterAdRequestDto adRequestDto, HttpServletRequest request) {
        return adService.saveAd(adRequestDto, request);
    }

    /**
     * 광고 사용 설정 여부 변경 - [광고관리]
     */
    @PostMapping("/ad/aduc")
    public Long updateAdUseConfig(@RequestBody AdUseConfigYnRequestDto adUseConfigYnRequestDto) {
       return adService.updateAdUseConfig(adUseConfigYnRequestDto);
    }

    /**
     * 광고 사용 설정 여부 변경(체크박스) - [광고관리]
     */
    @PostMapping("/ad/aducs")
    public void updateAdUseConfigs(@RequestBody AdUseConfigYnListRequestDto requestDtos, HttpServletRequest servletRequest) {
        adService.updateAdUseConfigs(requestDtos, servletRequest);
    }
    /**
     * 광고 활성 여부 변경(체크박스) - [광고관리]
     */
    @PostMapping("/ad/adayns")
    public void updateAdActYns(@RequestBody DeleteAdListRequestDto requestDtos, HttpServletRequest servletRequest) {
        adService.updateAdActYns(requestDtos, servletRequest);
    }


}
