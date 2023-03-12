package com.example.demo.service;

import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.controller.request.agroup.CreateAgroupReqDto;
import com.example.demo.controller.response.agroup.AgroupResponseDto;
import com.example.demo.entity.Adv;
import com.example.demo.entity.Agroup;
import com.example.demo.entity.Member;
import com.example.demo.entity.common.UserDetailsImpl;
import com.example.demo.repository.AdvRepository;
import com.example.demo.repository.AgroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgroupService {
    private final TokenProvider tokenProvider;
    private final AdvRepository advRepository;
    private final AgroupRepository agroupRepository;

    public AgroupResponseDto createAgroup(CreateAgroupReqDto createAgroupReqDto, HttpServletRequest request) {
        Member member = validateMember(request);
        Adv adv = isPresentAdv(member.getMemberId());

        Agroup agroup = createAgroupReqDto.createAgroup(adv);
        agroupRepository.save(agroup);


        return AgroupResponseDto.of(agroup);
    }

    public List<AgroupResponseDto> agroupList(HttpServletRequest request) {
        Member member = validateMember(request);
        Adv adv = isPresentAdv(member.getMemberId());

        List<Agroup> list = agroupRepository.findByAdv(adv);
        List<AgroupResponseDto> dtoList = new ArrayList<>() ;

        for (Agroup agroup: list) {
            dtoList.add(
                    AgroupResponseDto.builder()
                            .agroupId(agroup.getAgroupId())
                            .agroupName(agroup.getAgroupName())
                            .agroupActYn(agroup.getAgroupActYn())
                            .agroupUseYn(agroup.getAgroupUseYn())
//                            .regTime(agroup.getRegTime())
                            .build()

            );
        }

        return dtoList;
    }

    // 사용자 아이디 확인
    @Transactional(readOnly = true)
    public Adv isPresentAdv(String advId) {
        Optional<Adv> adv = advRepository.findById(advId);
        return adv.orElse(null);
    }

    @Transactional(readOnly = true)
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Authorization").substring(7))) {

            return null;
        }
        //Authentication에서 멤버 객체 불러오기
        return tokenProvider.getMemberFromAuthentication();
    }

}
