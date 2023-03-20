package com.example.demo.controller;

import com.example.demo.controller.request.agroup.CreateAgroupRequestDto;
import com.example.demo.controller.request.agroup.SearchAgroupRequestDto;
import com.example.demo.controller.request.agroup.UpdateAgUseConfigRequestDto;
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
     * 해당 광고주의 광고그룹 목록 조회 - [광고등록]
     */
    @GetMapping("/agroup")
    public ResponseEntity<List<AgroupResponseDto>> agroupList(HttpServletRequest request) {

        return ResponseEntity.ok().body(agroupService.agroupList(request));
    }

    /**
     * 광고그룹 생성 - [광고등록 / 광고관리]
     */
    @PostMapping("/agroup")
    public ResponseEntity<AgroupResponseDto> saveAgroup(@RequestBody CreateAgroupRequestDto createAgroupRequestDto, HttpServletRequest servletRequest) {
        return ResponseEntity.ok().body(agroupService.saveAgroup(createAgroupRequestDto, servletRequest));
    }

    /**
     * 광고그룹명 검색 - [광고관리]
     */
    @PostMapping("/agroup/list")
    public ResponseEntity<List<AgroupResponseDto>> searchAgroupList(@RequestBody SearchAgroupRequestDto agroupRequestDto, HttpServletRequest servletRequest) {
        return ResponseEntity.ok().body(agroupService.searchAgroupList(agroupRequestDto, servletRequest));
    }

    /**
     * 광고그룹 사용 설정 여부 변경 - [광고관리]
     */
    @PostMapping("agroup/useConfig")
    public void updateAgUseConfig(@RequestBody UpdateAgUseConfigRequestDto requestDto, HttpServletRequest servletRequest) {
        agroupService.updateAgUseConfig(requestDto, servletRequest);
    }
}
