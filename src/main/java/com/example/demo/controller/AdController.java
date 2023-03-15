package com.example.demo.controller;

import com.example.demo.controller.request.ad.RegisterAdRequestDto;
import com.example.demo.controller.response.ad.AdResponseDto;
import com.example.demo.controller.response.agroup.AgroupResponseDto;
import com.example.demo.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdController {
    private final AdService adService;

    /**
     * 광고 등록
     */
    @PostMapping("/ad")
    public ResponseEntity<AdResponseDto> saveAd(@RequestBody RegisterAdRequestDto adRequestDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(adService.saveAd(adRequestDto, request));
    }
}
