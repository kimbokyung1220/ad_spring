package com.example.demo.repository;

import com.example.demo.controller.request.kwd.KwdNameRequestDto;
import com.example.demo.controller.response.kwd.KwdDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface KwdDslRepository {
    List<KwdDto> searchKwdList(Long adId, KwdNameRequestDto requestDto);
    List<KwdDto> searchIspKwdList(String kwdNameDto);
}
