package com.example.demo.service;

import com.example.demo.controller.request.agroup.CreateAgroupRequestDto;
import com.example.demo.controller.request.agroup.SearchAgroupRequestDto;
import com.example.demo.controller.request.agroup.UpdateAgUseConfigRequestDto;
import com.example.demo.controller.response.agroup.AgroupListResponseDto;
import com.example.demo.controller.response.agroup.AgroupResponseDto;
import com.example.demo.entity.Adv;
import com.example.demo.entity.Agroup;
import com.example.demo.entity.Member;
import com.example.demo.repository.AgroupDslRepositoryImpl;
import com.example.demo.repository.AgroupRepository;
import com.example.demo.service.common.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgroupService {
    private final ValidationService validation;
    private final AgroupRepository agroupRepository;
    private final AgroupDslRepositoryImpl agroupDslRepository;

    /**
     * 광고그룹 생성
     */
    public AgroupResponseDto saveAgroup(CreateAgroupRequestDto createAgroupRequestDto, HttpServletRequest request) {
        Member member = validation.getMember(request);
        Adv adv = validation.isPresentAdv(member.getMemberId());

        if (!agroupRepository.existsByAgroupName(createAgroupRequestDto.getAgroupName())) {
            Agroup agroupInfo = createAgroupRequestDto.createAgroup(adv);
            agroupRepository.save(agroupInfo);
        }

        Agroup agroup = agroupRepository.findByAgroupName(createAgroupRequestDto.getAgroupName());

        return AgroupResponseDto.of(agroup);
    }

    /**
     * 해당 광고주의 광고그룹 목록 조회 - [광고등록]
     */
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
                            .agroupUseConfigYn(agroup.getAgroupUseConfigYn())
                            .regTime(agroup.getRegTime())
                            .build()

            );
        }

        return dtoList;
    }

    /**
     * 광고그룹 검색 - [광고관리]
     */
    public List<AgroupResponseDto> searchAgroupList(SearchAgroupRequestDto agroupRequestDto, HttpServletRequest request) {
        Member member = validation.getMember(request);
        Adv adv = validation.isPresentAdv(member.getMemberId());
        System.out.println("adv.getAdvId() *********");
        System.out.println(adv.getAdvId());
        List<AgroupListResponseDto> dtoList = agroupDslRepository.searchAgroupList(agroupRequestDto, adv);
        List<AgroupResponseDto> result = new ArrayList<>();

        for (AgroupListResponseDto dto : dtoList) {
            result.add(
                    AgroupResponseDto.agroupItem(dto)
            );
        }
        return result;
    }

    /** 광고그룹 사용 설정 여부 변경 - [광고관리] */
    @Transactional
    public void updateAgUseConfig(UpdateAgUseConfigRequestDto requestDto, HttpServletRequest servletRequest) {
        Agroup agroup = validation.isPresentAgroup(requestDto.getAgroupId());
        agroup.updateAgUseConfig(requestDto);
    }
}
