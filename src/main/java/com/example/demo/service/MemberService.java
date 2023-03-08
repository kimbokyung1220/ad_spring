package com.example.demo.service;

import com.example.demo.config.jwt.TokenDto;
import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.controller.request.AddBalanceRequestDto;
import com.example.demo.controller.request.MemberRequestDto;
import com.example.demo.controller.response.MemberResponseDto;
import com.example.demo.entity.Adv;
import com.example.demo.entity.Member;
import com.example.demo.repository.AdvRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
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
        Optional<Member> member = Optional.ofNullable(memberRepository.findById(memberRequestDto.getMemberId())
                .orElseThrow(() -> new NullPointerException("회원이 없습니다.")));

        /**
         * 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
         *  [UsernamePasswordAuthenticationFilter] : 아이디, 패스워드를 이용한 인증을 담당하는 필터
         *    1)사용자가 입력한 ID/PW로 인증정보 객체를 UsernamePasswordAuthenticationToken을 생성
         *    2)아직 인증이 완료된 객체가 아니며 AuthenticationManager 에서 authenticate 메소드의 파라미터로 넘겨서 검증 후에 Authentication를 받음
         */
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        /**
         * 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
         *   1)위 토큰이 유효한지 AuthenticationManager에 위임
         *   2) Builder 에서 UserDetails 의 유저 정보가 서로 일치하는지 검사
         *   인증 프로바이더의 Default : UserDetailsService
         *   3) authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
         *      => 이 결과값을 가지고 Authentication 객체 생성 -> SecurityContext에 저장
         */
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        // header에 Token값 add
        tokenDto.setTokenToHeaders(response);

        // 5. 토큰 발급
        return tokenDto;
    }
}
