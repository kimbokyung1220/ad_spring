package com.example.demo.service;

import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.controller.request.agroup.CreateAgroupReqDto;
import com.example.demo.controller.response.AgroupResponseDto;
import com.example.demo.entity.Adv;
import com.example.demo.entity.Agroup;
import com.example.demo.entity.Member;
import com.example.demo.repository.AdvRepository;
import com.example.demo.repository.AgroupRepository;
import com.example.demo.service.common.ValidationService;
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
    private final ValidationService validation;
    private final AgroupRepository agroupRepository;

    public AgroupResponseDto saveAgroup(CreateAgroupReqDto createAgroupReqDto, HttpServletRequest request) {
        Member member = validation.getMember(request);
        Adv adv = validation.isPresentAdv(member.getMemberId());

        if (!agroupRepository.existsByAgroupName(createAgroupReqDto.getAgroupName())) {
            Agroup agroupInfo = createAgroupReqDto.createAgroup(adv);
            agroupRepository.save(agroupInfo);
        }

        Agroup agroup = agroupRepository.findByAgroupName(createAgroupReqDto.getAgroupName());

        return AgroupResponseDto.of(agroup);
    }

    public List<AgroupResponseDto> agroupList(HttpServletRequest request) {
        Member member = validation.getMember(request);
        Adv adv = validation.isPresentAdv(member.getMemberId());

        List<Agroup> list = agroupRepository.findByAdv(adv);
        List<AgroupResponseDto> dtoList = new ArrayList<>();

        for (Agroup agroup : list) {
            dtoList.add(
                    AgroupResponseDto.builder()
                            .agroupId(agroup.getAgroupId())
                            .agroupName(agroup.getAgroupName())
                            .agroupActYn(agroup.getAgroupActYn())
                            .agroupUseYn(agroup.getAgroupUseYn())
                            .regTime(agroup.getRegTime())
                            .build()

            );
        }

        return dtoList;
    }
}
