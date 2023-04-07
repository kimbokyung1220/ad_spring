package com.example.demo.controller;

import com.example.demo.controller.response.ResponseDto;
import com.example.demo.controller.response.dadRpt.DadRptDto;
import com.example.demo.service.DadRptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class DadRptController {
    private final DadRptService dadRptService;

    /** 통계자료 - 차트 */
    @PostMapping(value = "/rpt/{dadDetId}")
    public ResponseDto<List<DadRptDto>> getDadRptDetail(@PathVariable Long dadDetId) {
        return dadRptService.getDadRptDetail(dadDetId);
    }
}
