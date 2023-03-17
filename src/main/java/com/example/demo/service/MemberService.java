package com.example.demo.service;

import com.example.demo.config.jwt.TokenDto;
import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.controller.request.adv.AddBalanceRequestDto;
import com.example.demo.controller.request.member.MemberRequestDto;
import com.example.demo.controller.response.MemberResponseDto;
import com.example.demo.entity.Adv;
import com.example.demo.entity.Member;
import com.example.demo.repository.AdvRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final AdvRepository advRepository;
    private final ValidationUtil validationUtil;

    /**
     * 회원가입
     */
    @Transactional
    public MemberResponseDto signup(String path, MemberRequestDto memberRequestDto) {
        // 회원가입이 되어있는지 check
        String memberId = validationUtil.existMember(memberRequestDto.getMemberId());

        // 광고주
        if (path.equals("adv")) {
            Member member = memberRequestDto.toAdv(passwordEncoder);
            memberRepository.save(member);

            AddBalanceRequestDto addBalanceRequestDto = new AddBalanceRequestDto(member.getMemberId());
            advRepository.save(new Adv(member).addBalance(addBalanceRequestDto));
            System.out.println(member.getMemberId());


            // 관리자
        } else if (path.equals("admin")) {
            Member member = memberRequestDto.toAdmin(passwordEncoder);
            return MemberResponseDto.of(Optional.of(memberRepository.save(member)));
        }

        Optional<Member> member = memberRepository.findById(memberId);

        return MemberResponseDto.of(member);
    }

    /**
     * 로그인
     */
    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto, HttpServletResponse response) {

        Member member = isPresentMember(memberRequestDto.getMemberId());

        //인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(member);
        tokenDto.setTokenToHeaders(response);

        // 5. 토큰 발급
        return tokenDto;
    }

    // 사용자 아이디 확인
    @Transactional(readOnly = true)
    public Member isPresentMember(String memberId) {
        Optional<Member> Member = memberRepository.findById(memberId);
        return Member.orElse(null);
    }

    // 헤더에 담기는 토큰
    private void tokenToHeaders(TokenDto tokenDto, HttpServletResponse response) {
        response.addHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
        response.addHeader("Refresh_Token", tokenDto.getRefreshToken());
        response.addHeader("Access-Token-Expire-Time", tokenDto.getAccessTokenExpiresIn().toString());
    }

}
