package com.example.demo.service;

import com.example.demo.controller.request.agroup.*;
import com.example.demo.controller.response.agroup.AgroupItemResponseDto;
import com.example.demo.controller.response.agroup.AgroupListResponseDto;
import com.example.demo.controller.response.agroup.AgroupResponseDto;
import com.example.demo.entity.Adv;
import com.example.demo.entity.Agroup;
import com.example.demo.entity.Member;
import com.example.demo.repository.AgroupDslRepositoryImpl;
import com.example.demo.repository.AgroupRepository;
import com.example.demo.service.common.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
     * 광고그룹 생성 - [광고등록]
     */
    public AgroupResponseDto saveAgroup(CreateAgroupRequestDto createAgroupRequestDto, HttpServletRequest request) {
        Member member = validation.getMember(request);
        Adv adv = validation.isPresentAdv(member.getMemberId());

        if (!agroupRepository.existsByAgroupName(createAgroupRequestDto.getAgroupName())) {
            Agroup agroupInfo = createAgroupRequestDto.createAgroup();
            agroupRepository.save(agroupInfo);
        }

        Agroup agroup = agroupRepository.findByAgroupName(createAgroupRequestDto.getAgroupName());

        return AgroupResponseDto.of(agroup);
    }

    /**
     * 해당 광고주의 광고그룹 목록 전체 조회 - [광고등록]
     */
    public List<AgroupResponseDto> agroupAllList(HttpServletRequest request) {
        List<Agroup> list = agroupRepository.findAll(Sort.by(Sort.Direction.DESC, "regTime"));
        List<AgroupResponseDto> dtoList = new ArrayList<>();

        for (Agroup agroup : list) {
            dtoList.add(
                    AgroupResponseDto.of(agroup)
            );
        }

        return dtoList;
    }

    /**
     * 광고그룹 리스트 검색 - [광고관리]
     */
    public List<AgroupResponseDto> searchAgroupList(AgroupNameRequestDto agroupRequestDto, HttpServletRequest request) {
        Member member = validation.getMember(request);
        Adv adv = validation.isPresentAdv(member.getMemberId());
        List<AgroupListResponseDto> dtoList = agroupDslRepository.searchAgroupList(agroupRequestDto);
        List<AgroupResponseDto> result = new ArrayList<>();

        for (AgroupListResponseDto dto : dtoList) {
            result.add(
                    AgroupResponseDto.agroupItemList(dto)
            );
        }
        return result;
    }
    /**
     * 광고그룹 상세화면 - [광고관리]
     */
    public AgroupResponseDto agroupDetail(AgroupIdRequestDto dto, HttpServletRequest servletRequest) {
        AgroupItemResponseDto agroupInfo = agroupDslRepository.agroupDetail(dto);
        return AgroupResponseDto.agroupItem(agroupInfo);
    }

    /**
     * 광고그룹 사용 설정 여부 변경 - [광고관리]
     */
    @Transactional
    public void updateAgUseConfig(UpdateAgUseConfigRequestDto requestDto, HttpServletRequest servletRequest) {
        Agroup agroup = validation.isPresentAgroup(requestDto.getAgroupId());
        agroup.updateAgUseConfig(requestDto);
    }

    /**
     * 광고그룹 사용 설정 여부 변경(체크박스) - [광고관리]
     */
    @Transactional
    public void updateAgUseConfigs(UpdateAgUseConfigListRequestDto requestDtos, HttpServletRequest servletRequest) {
        List<UpdateAgUseConfigRequestDto> agUseConfigList = requestDtos.getAgUseConfigList();
        if (requestDtos.getCode() == 1) {
            for (int i = 0; i < agUseConfigList.size(); i++) {
                Agroup agroup = validation.isPresentAgroup(agUseConfigList.get(i).getAgroupId());
                agroup.updateOnAgUseConfig();
            }
        } else {
            for (int i = 0; i < agUseConfigList.size(); i++) {
                Agroup agroup = validation.isPresentAgroup(agUseConfigList.get(i).getAgroupId());
                agroup.updateOffAgUseConfig();
            }
        }
    }

    /**
     * 광고그룹 활성 여부 변경(체크박스) - [광고관리]
     */
    @Transactional
    public void updateAgActYns(DeleteAgroupListRequestDto requestDtos, HttpServletRequest servletRequest) {
        List<DeleteAgroupRequestDto> deleteAgList = requestDtos.getDeleteGroupList();
        for (int i = 0; i < deleteAgList.size(); i++) {
            Agroup agroup = validation.isPresentAgroup(deleteAgList.get(i).getAgroupId());
            agroup.updateOffActYn();
        }
    }
    /** 그룹명 변경 - [광고관리] */
    @Transactional
    public void updateAdGroupName(UpdateAgroupNameRequestDto requestDto, HttpServletRequest servletRequest) {

        Agroup agroup = agroupRepository.findByAgroupName(requestDto.getAgroupName());

        if(agroupRepository.existsByAgroupName(requestDto.getNewAgroupName())) {
            System.out.println("기존에 있는 그룹명입니다.");
            return;
        }
        agroup.updateAgroupName(requestDto);
    }
}
