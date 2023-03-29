package com.example.demo.controller;

import com.example.demo.controller.request.daddet.DadUseConfigYnListRequestDto;
import com.example.demo.controller.request.daddet.DadUseConfigYnRequestDto;
import com.example.demo.controller.request.daddet.DeleteDadListRequestDto;
import com.example.demo.controller.request.kwd.KwdNameRequestDto;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.controller.response.dadDet.DadDetDto;
import com.example.demo.controller.response.dadDet.IspAdListResponseDto;
import com.example.demo.service.DadDetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
