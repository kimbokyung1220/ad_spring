package com.example.demo.controller;

import com.example.demo.controller.request.kwd.*;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.controller.response.kwd.KwdResponseDto;
import com.example.demo.service.KwdService;
import lombok.RequiredArgsConstructor;
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

    /**
     * 키워드 사용 설정 여부 변경 - [광고관리]
     */
    @PostMapping("/kwd/kuc")
    public ResponseDto<String> updateKwdUseConfig(@RequestBody KwdIdRequestDto requestDto, HttpServletRequest servletRequest) {
        return kwdService.updateKwdUseConfig(requestDto);
    }
    /**
     * 키워드 사용 설정 여부 변경(체크박스) - [광고관리]
     */
    @PostMapping("/kwd/kucs")
    public ResponseDto<String> updateKwdUseConfigs(@RequestBody KwdListRequestDto requestDto, HttpServletRequest servletRequest) {
        return kwdService.updateKwdUseConfigs(requestDto);
    }

    /**
     * 키워드 활성 여부 변경(체크박스) - [광고관리]
     */
    @PostMapping("/kwd/kacts")
    public ResponseDto<String> updateActYn(@RequestBody DeleteKwdListRequestDto requestDto, HttpServletRequest servletRequest) {
        return kwdService.updateActYn(requestDto, servletRequest);
    }
}
