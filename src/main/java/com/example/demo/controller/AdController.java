package com.example.demo.controller;

import com.example.demo.controller.request.ad.AdRequestDto;
import com.example.demo.service.AdService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/ad")
    public void saveAd(@RequestBody AdRequestDto adRequestDto, HttpServletRequest request) {
        adService.saveAd(adRequestDto, request);
    }
}
