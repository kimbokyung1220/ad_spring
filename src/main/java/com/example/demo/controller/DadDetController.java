package com.example.demo.controller;

import com.example.demo.controller.request.daddet.updateIspAdDadCrnRequestDto;
import com.example.demo.controller.request.kwd.KwdNameRequestDto;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.controller.response.dadDet.DadDetDto;
import com.example.demo.controller.response.dadDet.IspAdListResponseDto;
import com.example.demo.service.DadDetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DadDetController {
    private final DadDetService dadDetService;

    /**
     * 광고 검수 대상 리스트
     */
    @PostMapping("/dad/list")
    public ResponseDto<List<DadDetDto>> searchIspAdList(@RequestBody KwdNameRequestDto kwdNameRequestDto) {
        return dadDetService.searchIspAdList(kwdNameRequestDto);
    }

    /**
     * 광고 검수 대상 상세조회 (검수처리)
     */
    @PostMapping("/dad/list/{dadDetId}")
    public ResponseDto<IspAdListResponseDto> IspAdDetail(@PathVariable Long dadDetId) {
        return dadDetService.IspAdDetail(dadDetId);
    }
    /**
     * 광고 검수 대상 검수처리 (반려 / 승인)
     */
    @PostMapping("/dad/status")
    public ResponseDto<List<DadDetDto>> updateIspAdDadCrn(@RequestBody updateIspAdDadCrnRequestDto requestDto) {
        return dadDetService.updateIspAdDadCrn(requestDto);
    }
    /**
     * 광고현황
     */
    @PostMapping("/dad/cslist")
    public ResponseDto<List<DadDetDto>> csAdAllList() {
        return dadDetService.csAdAllList();
    }
}
