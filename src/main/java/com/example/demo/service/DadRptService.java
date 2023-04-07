package com.example.demo.service;

import com.example.demo.controller.response.ResponseDto;
import com.example.demo.controller.response.dadRpt.DadRptDto;
import com.example.demo.repository.DadRptRepository;
import com.example.demo.repository.JdbcTemplateDadRptRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DadRptService {
    private final JdbcTemplateDadRptRepositoryImpl dadRptRepository;
    
    /** 통계자료 - 차트 */
    public ResponseDto<List<DadRptDto>> getDadRptDetail(Long dadDetId) {
        List<DadRptDto> rptData = dadRptRepository.getDadRptDetail(dadDetId);

        return ResponseDto.success(rptData);
    }
}
