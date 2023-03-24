package com.example.demo.controller;

import com.example.demo.controller.request.item.ItemNoNameRequestDto;
import com.example.demo.controller.request.kwd.KwdNameRequestDto;
import com.example.demo.controller.request.kwd.KwdRequestDto;
import com.example.demo.controller.response.item.MngItemResponseDto;
import com.example.demo.controller.response.kwd.KwdResponseDto;
import com.example.demo.entity.Kwd;
import com.example.demo.service.KwdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class KwdController {
    private final KwdService kwdService;

    /** 키워드 조건조회 - [광고관리] */
    @PostMapping("/kwd/list/{adId}")
    public ResponseEntity<List<KwdResponseDto>> searchKwdList(@PathVariable Long adId, @RequestBody KwdNameRequestDto requestDto, HttpServletRequest servletRequest) {

        return ResponseEntity.ok().body(kwdService.searchKwdList(adId, requestDto, servletRequest));
    }
}
