package com.example.demo.service;

import com.example.demo.controller.request.kwd.*;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.controller.response.kwd.KwdDto;
import com.example.demo.controller.response.kwd.KwdResponseDto;
import com.example.demo.entity.Ad;
import com.example.demo.entity.DadDet;
import com.example.demo.entity.Kwd;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.DadDetRepository;
import com.example.demo.repository.KwdDslRepositoryImpl;
import com.example.demo.repository.KwdRepository;
import com.example.demo.service.common.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KwdService {
    private final KwdDslRepositoryImpl kwdDslRepository;
    private final KwdRepository kwdRepository;
    private final DadDetRepository dadDetRepository;
    private final ValidationService validation;

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

    /**
     * 키워드 ON/OFF
     */
    @Transactional
    public ResponseDto<String> updateKwdUseConfig(KwdIdRequestDto requestDto) {
        Kwd kwd = validation.isPresentKwdId(requestDto.getKwdId());
        DadDet dadDet = dadDetRepository.findByKwd(kwd);
        dadDet.updateItemDadUseConfig(requestDto.getSellPossKwdYn());
        return ResponseDto.success("변경 되었습니다.");
    }

    /**
     * 키워드 ON/OFF (체크박스)
     */
    @Transactional
    public ResponseDto<List<KwdIdRequestDto>> updateKwdUseConfigs(KwdListRequestDto requestDto) {
        List<KwdIdRequestDto> kwdList = requestDto.getKwdList();
        if (kwdList.isEmpty()) {
            return ResponseDto.fail(ErrorCode.NOT_SELECTED_ADGROUP.getCode(), ErrorCode.NOT_SELECTED_ADGROUP.getMessage());
        }

        if (requestDto.getCode() == 1) {
            for (int i = 0; i < kwdList.size(); i++) {
                Kwd kwd = validation.isPresentKwdId(kwdList.get(i).getKwdId());
                Ad ad = validation.isPresentAd(kwdList.get(i).getAdId());
                DadDet dadDet = dadDetRepository.findByKwdAndAd(kwd, ad);
                dadDet.updateOnDadUseConfig();
            }
        } else {
            for (int i = 0; i < kwdList.size(); i++) {
                Kwd kwd = validation.isPresentKwdId(kwdList.get(i).getKwdId());
                Ad ad = validation.isPresentAd(kwdList.get(i).getAdId());
                DadDet dadDet = dadDetRepository.findByKwdAndAd(kwd, ad);
                dadDet.updateOffDadUseConfig();
            }
        }

        return ResponseDto.success(kwdList);
    }
    @Transactional
    public ResponseDto<String> updateActYn(DeleteKwdListRequestDto requestDto, HttpServletRequest servletRequest) {
        List<DeleteKwdRequestDto> deleteKwdList = requestDto.getDeleteKwdList();

        if(deleteKwdList.isEmpty()) {
            return ResponseDto.fail(ErrorCode.NOT_SELECTED_ADGROUP.getCode(), ErrorCode.NOT_SELECTED_ADGROUP.getMessage());
        }
        for (int i = 0; i < deleteKwdList.size(); i++) {
            Kwd kwd = validation.isPresentKwdId(deleteKwdList.get(i).getKwdId());
            DadDet dadDet = dadDetRepository.findByKwd(kwd);
            dadDet.updateOffDadUseConfig();
            dadDet.updateOffDadActYn();
        }
        return ResponseDto.success("변경 완료되었습니다.");
    }
}
