package com.example.demo.controller;

import com.example.demo.controller.request.agroup.*;
import com.example.demo.controller.response.agroup.AgroupResponseDto;
import com.example.demo.service.AgroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AgroupController {
    private final AgroupService agroupService;

    /**
     * 광고그룹 전체조회 - [광고등록]
     */
    @GetMapping("/agroup")
    public ResponseEntity<List<AgroupResponseDto>> agroupAllList(HttpServletRequest request) {

        return ResponseEntity.ok().body(agroupService.agroupAllList(request));
    }

    /**
     * 광고그룹 생성 - [광고등록 / 광고관리]
     */
    @PostMapping("/agroup")
    public ResponseEntity<AgroupResponseDto> saveAgroup(@RequestBody CreateAgroupRequestDto createAgroupRequestDto, HttpServletRequest servletRequest) {
        return ResponseEntity.ok().body(agroupService.saveAgroup(createAgroupRequestDto, servletRequest));
    }

    /**
     * 광고그룹명 조건 검색 - [광고관리]
     */
    @PostMapping("/agroup/list")
    public ResponseEntity<List<AgroupResponseDto>> searchAgroupList(@RequestBody AgroupNameRequestDto agroupRequestDto, HttpServletRequest servletRequest) {
        return ResponseEntity.ok().body(agroupService.searchAgroupList(agroupRequestDto, servletRequest));
    }

    /**
     * 광고그룹 상세화면 - [광고관리]
     */
    @PostMapping("/agroup/aginfo")
    public ResponseEntity<AgroupResponseDto> agroupDetail(@RequestBody AgroupIdRequestDto requestDto, HttpServletRequest servletRequest) {
        return ResponseEntity.ok().body(agroupService.agroupDetail(requestDto, servletRequest));
    }

    /**
     * 광고그룹 사용 설정 여부 변경 - [광고관리]
     */
    @PostMapping("/agroup/aguc")
    public void updateAgUseConfig(@RequestBody UpdateAgUseConfigRequestDto requestDto, HttpServletRequest servletRequest) {
        agroupService.updateAgUseConfig(requestDto, servletRequest);
    }

    /**
     * 광고그룹 사용 설정 여부 변경(체크박스) - [광고관리]
     */
    @PostMapping("/agroup/agucs")
    public void updateAgUseConfigs(@RequestBody UpdateAgUseConfigListRequestDto requestDtos, HttpServletRequest servletRequest) {
        agroupService.updateAgUseConfigs(requestDtos, servletRequest);
    }
    /**
     * 광고그룹 활성 여부 변경(체크박스) - [광고관리]
     */
    @PostMapping("/agroup/agayns")
    public void updateAgActYns(@RequestBody DeleteAgroupListRequestDto requestDtos, HttpServletRequest servletRequest) {
        agroupService.updateAgActYns(requestDtos, servletRequest);
    }

    /** 그룹명 변경 - [광고관리] */
    @PostMapping("/agroup/agname")
    public void updateAdGroupName(@RequestBody UpdateAgroupNameRequestDto requestDto, HttpServletRequest servletRequest) {
        agroupService.updateAdGroupName(requestDto, servletRequest);

    }
}
