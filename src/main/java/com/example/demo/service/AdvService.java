package com.example.demo.service;

import com.example.demo.controller.request.adv.AdActYnRequestDto;
import com.example.demo.controller.request.adv.DayLimitBudgetRequestDto;
import com.example.demo.controller.response.AdvResponseDto;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.entity.Adv;
import com.example.demo.entity.Member;
import com.example.demo.exception.ErrorCode;
import com.example.demo.service.common.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdvService {
    private final ValidationService validation;

    /**
     * 광고주 계정 조회
     */
    public AdvResponseDto getAdvInfo(HttpServletRequest request) {
        Member member = validation.getMember(request);
        Adv adv = validation.isPresentAdv(member.getMemberId());

        return AdvResponseDto.advInfo(adv);
    }

    /** 광고주 - 광고 진행 활성 여부 변경 */
    @Transactional
    public AdvResponseDto updateIngActYn(AdActYnRequestDto adActYnRequestDto, HttpServletRequest request) {
        Member member = validation.getMember(request);
        Adv adv = validation.isPresentAdv(member.getMemberId());
        adv.updateIngActYn(adActYnRequestDto);

        return AdvResponseDto.advInfo(adv);
    }

    /**
     *  일일 허용 예산 변경 - [광고관리]
     */
    @Transactional
    public ResponseDto<AdvResponseDto> updateLimitBudget(DayLimitBudgetRequestDto dayLimitBudgetRequestDto, HttpServletRequest request) {
        Member member = validation.getMember(request);
        Adv adv = validation.isPresentAdv(member.getMemberId());

        Integer dayLimitBudget = dayLimitBudgetRequestDto.getDayLimitBudget();
        String dayLimitBudgetStr = String.valueOf(dayLimitBudgetRequestDto.getDayLimitBudget());

        if(dayLimitBudgetStr.length() >= 2 && !dayLimitBudgetStr.substring(dayLimitBudgetStr.length()-2, dayLimitBudgetStr.length()).equals("00")) {
            return ResponseDto.fail(ErrorCode.DAY_LIMIT_MEASURE.getCode(), ErrorCode.DAY_LIMIT_MEASURE.getMessage());
        }

        if(dayLimitBudget < 100 && dayLimitBudget >= 1) {
            return ResponseDto.fail(ErrorCode.DAY_LIMIT_MEASURE.getCode(), ErrorCode.DAY_LIMIT_MEASURE.getMessage());
        }

        adv.updateLimitBudget(dayLimitBudgetRequestDto);

        return ResponseDto.success(AdvResponseDto.advInfo(adv));
    }
}
