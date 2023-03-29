package com.example.demo.repository;

import com.example.demo.controller.request.kwd.KwdNameRequestDto;
import com.example.demo.controller.response.dadDet.DadDetDto;
import com.example.demo.controller.response.kwd.KwdDto;

import java.util.List;


public interface DadDetDslRepository {
    List<DadDetDto> searchIspAdList(String kwdNameDto);

}
