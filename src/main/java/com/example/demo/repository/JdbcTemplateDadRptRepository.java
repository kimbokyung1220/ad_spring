package com.example.demo.repository;

import com.example.demo.controller.response.dadRpt.DadRptDto;

import java.util.List;

public interface JdbcTemplateDadRptRepository {
    List<DadRptDto> getDadRptDetail(Long dadDetId);
}
