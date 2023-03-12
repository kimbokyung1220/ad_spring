//package com.example.demo.service;
//
//import com.example.demo.entity.Member;
//import com.example.demo.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
//        return memberRepository.findById(memberId)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new UsernameNotFoundException(memberId + " -> 데이터베이스에서 찾을 수 없습니다."));
//    }
//
//    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
//    private UserDetails createUserDetails(Member member) {
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getRoleGroup().toString());
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(grantedAuthority);
//
//        return new User(
//                String.valueOf(member.getMemberId()),
//                member.getPwd(),
//                Collections.singleton(grantedAuthority)
//        );
//    }
//}
