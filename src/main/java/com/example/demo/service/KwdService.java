package com.example.demo.service;

import com.example.demo.controller.request.kwd.KwdNameRequestDto;
import com.example.demo.controller.response.kwd.KwdDto;
import com.example.demo.controller.response.kwd.KwdResponseDto;
import com.example.demo.repository.KwdDslRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KwdService {
    private final KwdDslRepositoryImpl kwdDslRepository;

    public List<KwdResponseDto> searchKwdList(Long adId, KwdNameRequestDto requestDto, HttpServletRequest servletRequest) {
        List<KwdDto> dtoList = kwdDslRepository.searchKwdList(adId, requestDto);
        List<KwdResponseDto> result = new ArrayList<>();
        for (KwdDto dto : dtoList) {
            result.add(
                    KwdResponseDto.KwdList(dto)
            );
        }
        return result;
    }

}
