package com.example.demo.controller;

import com.example.demo.config.jwt.TokenDto;
import com.example.demo.controller.request.member.MemberRequestDto;
import com.example.demo.controller.response.member.MemberResponseDto;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/common")
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping(value ="/{path}/signup")
    public ResponseEntity<MemberResponseDto> signup(@PathVariable String path, @RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok().body(memberService.signup(path, memberRequestDto));
    }

    /**
     * 로그인
     */
    @PostMapping(value ="/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) {
        return ResponseEntity.ok(memberService.login(memberRequestDto, response));
    }

}
