package com.example.demo.controller;

import com.example.demo.controller.request.agroup.CreateAgroupRequestDto;
import com.example.demo.controller.request.agroup.SearchAgroupRequestDto;
import com.example.demo.controller.response.agroup.AgroupListResponseDto;
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

    /** 광고주에 해당하는 광고그룹 목록 */
    @GetMapping("/agroup")
    public ResponseEntity<List<AgroupResponseDto>> agroupList(HttpServletRequest request) {

        return ResponseEntity.ok().body(agroupService.agroupList(request));
    }

    /** 광고그룹 생성 */
    @PostMapping("/agroup")
    public ResponseEntity<AgroupResponseDto> saveAgroup(@RequestBody CreateAgroupRequestDto createAgroupRequestDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(agroupService.saveAgroup(createAgroupRequestDto, request));
    }

    /** 광고그룹명 검색 */
    @PostMapping("/agroup/list")
    public ResponseEntity<List<AgroupResponseDto>> searchAgroupList(@RequestBody SearchAgroupRequestDto agroupRequestDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(agroupService.searchAgroupList(agroupRequestDto, request));
    }
}
