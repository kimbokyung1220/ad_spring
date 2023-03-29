package com.example.demo.controller;

import com.example.demo.controller.request.kwd.*;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.controller.response.kwd.KwdDto;
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

    /**
     * 키워드 조건조회 - [광고관리]
     */
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
    public void updateKwdUseConfigs(@RequestBody KwdListRequestDto requestDto, HttpServletRequest servletRequest) {
        kwdService.updateKwdUseConfigs(requestDto);
    }

    /**
     * 키워드 활성 여부 변경(체크박스) - [광고관리]
     */
    @PostMapping("/kwd/kacts")
    public ResponseDto<String> updateActYn(@RequestBody DeleteKwdListRequestDto requestDto, HttpServletRequest servletRequest) {
        return kwdService.updateActYn(requestDto, servletRequest);
    }
    /**
     * 검수 대상 키워드 조회 - [키워드 검수]
     */
    @PostMapping("/kwd/isp/list")
    public ResponseDto<List<KwdDto>> searchIspKwdList(@RequestBody KwdNameRequestDto kwdNameRequestDto) {
        return kwdService.searchIspKwdList(kwdNameRequestDto);
    }

    /**
     * 검수 대상 키워드 등록
     */
    @PostMapping("/kwd/isp")
    public ResponseDto<List<KwdDto>> saveIspKwd(@RequestBody KwdNameRequestDto kwdNameRequestDto) {
        return kwdService.saveIspKwd(kwdNameRequestDto);
    }

    /**
     * 검수 대상 키워드 삭제
     * 수동 검수 여부 1 => 0으로 update
     */
    @PostMapping("/kwd/isp/{kwdId}")
    public ResponseDto<List<KwdDto>> updateOffIspKwdManualYn(@PathVariable Long kwdId) {
        return kwdService.updateOffIspKwdManualYn(kwdId);
    }

}
