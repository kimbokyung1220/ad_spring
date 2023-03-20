package com.example.demo.controller;

import com.example.demo.controller.request.adv.AdActYnRequestDto;
import com.example.demo.controller.request.adv.DayLimitBudgetRequestDto;
import com.example.demo.controller.response.AdvResponseDto;
import com.example.demo.service.AdvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdvController {
    private final AdvService advService;

    /** 광고주 조회 */
    @GetMapping("/adv")
    public ResponseEntity<AdvResponseDto> getAdvInfo(HttpServletRequest request) {
        return ResponseEntity.ok().body(advService.getAdvInfo(request));
    }

    /** 광고주 - 광고 진행 활성 여부 변경 */
    @PostMapping("/adv/ad-act")
    public ResponseEntity<AdvResponseDto> updateIngActYn(@RequestBody AdActYnRequestDto adActYnRequestDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(advService.updateIngActYn(adActYnRequestDto, request));
    }

    /** 일일 허용 예산 변경 */
    @PostMapping("/adv/ad-budget")
    public ResponseEntity<AdvResponseDto> updateAdAct(@RequestBody DayLimitBudgetRequestDto dayLimitBudgetRequestDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(advService.updateLimitBudget(dayLimitBudgetRequestDto, request));
    }
}
