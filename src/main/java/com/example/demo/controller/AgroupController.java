package com.example.demo.controller;

import com.example.demo.controller.request.agroup.CreateAgroupReqDto;
import com.example.demo.controller.response.agroup.AgroupResponseDto;
import com.example.demo.entity.common.UserDetailsImpl;
import com.example.demo.service.AgroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AgroupController {
    private final AgroupService agroupService;

    /**
     * 광고주에 해당하는 광고그룹 목록
     * param: adv
     */
    @GetMapping("/agroup")
    public ResponseEntity<List<AgroupResponseDto>> agroupList(HttpServletRequest request) {

        return ResponseEntity.ok().body(agroupService.agroupList(request));
    }

    /**
     * 광고그룹 생성
     */
    @PostMapping("/agroup")
    public ResponseEntity<AgroupResponseDto> createAgroup(@RequestBody CreateAgroupReqDto createAgroupReqDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(agroupService.createAgroup(createAgroupReqDto, request));
    }
}
