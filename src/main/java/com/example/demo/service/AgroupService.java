package com.example.demo.service;

import com.example.demo.controller.request.agroup.*;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.controller.response.agroup.AgroupItemResponseDto;
import com.example.demo.controller.response.agroup.AgroupListResponseDto;
import com.example.demo.controller.response.agroup.AgroupResponseDto;
import com.example.demo.entity.Ad;
import com.example.demo.entity.Adv;
import com.example.demo.entity.Agroup;
import com.example.demo.entity.Member;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.AdRepository;
import com.example.demo.repository.AgroupDslRepositoryImpl;
import com.example.demo.repository.AgroupRepository;
import com.example.demo.service.common.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgroupService {
    private final ValidationService validation;
    private final AgroupRepository agroupRepository;
    private final AgroupDslRepositoryImpl agroupDslRepository;
    private final AdRepository adRepository;

    /**
     * 광고그룹 생성 - [광고등록]
     */
    public ResponseDto<AgroupResponseDto> saveAgroup(CreateAgroupRequestDto createAgroupRequestDto, HttpServletRequest request) {

        if (agroupRepository.existsByAgroupName(createAgroupRequestDto.getAgroupName())) {
            return ResponseDto.fail(ErrorCode.EXIST_AD_GROUP.getCode(), ErrorCode.EXIST_AD_GROUP.getMessage());
        }
        Agroup agroupInfo = createAgroupRequestDto.createAgroup();
//        Agroup agroup = agroupRepository.findByAgroupName(createAgroupRequestDto.getAgroupName());
        Agroup agroup = agroupRepository.save(agroupInfo);

        return ResponseDto.success(AgroupResponseDto.of(agroup));
    }

    /**
     * 해당 광고주의 광고그룹 목록 전체 조회 - [광고등록]
     */
    public ResponseDto<List<AgroupResponseDto>> agroupAllList(HttpServletRequest request) {
        List<AgroupResponseDto> agroupList = agroupRepository.findAgroupByAgroupActYnLikeOrderByRegTime(1)
                .stream()
                .map(agroup -> AgroupResponseDto.of(agroup))
                .collect(Collectors.toList());

        return ResponseDto.success(agroupList);
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
    public ResponseDto<String> updateAgUseConfig(UpdateAgUseConfigRequestDto requestDto, HttpServletRequest servletRequest) {
        Agroup agroup = validation.isPresentAgroup(requestDto.getAgroupId());
        Ad ad = adRepository.findByAgroup(agroup);
        ad.updateOffAdUseConfig();
        ad.updateOffAdActYn();
        agroup.updateAgUseConfig(requestDto);

        String value = requestDto.getAgroupUseConfigYn() == 1 ? "ON" : "OFF";
        return ResponseDto.success("광고그룹 사용여부가 " + value + " 으로 변경되었습니다.");
    }

    /**
     * 광고그룹 사용 설정 여부 변경(체크박스) - [광고관리]
     */
    @Transactional
    public ResponseDto<String> updateAgUseConfigs(UpdateAgUseConfigListRequestDto requestDtos, HttpServletRequest servletRequest) {
        List<UpdateAgUseConfigRequestDto> agUseConfigList = requestDtos.getAgUseConfigList();
        if(agUseConfigList.isEmpty()) {
            return ResponseDto.fail(ErrorCode.NOT_SELECTED_ADGROUP.getCode(), ErrorCode.NOT_SELECTED_ADGROUP.getMessage());
        }

        if (requestDtos.getCode() == 1) {
            for (int i = 0; i < agUseConfigList.size(); i++) {
                Agroup agroup = validation.isPresentAgroup(agUseConfigList.get(i).getAgroupId());
                agroup.updateOnAgUseConfig();
            }
            return ResponseDto.success(ErrorCode.ADGROUP_ACT_ON.getMessage());
        } else {
            for (int i = 0; i < agUseConfigList.size(); i++) {
                Agroup agroup = validation.isPresentAgroup(agUseConfigList.get(i).getAgroupId());
                agroup.updateOffAgUseConfig();
            }
            return ResponseDto.success(ErrorCode.ADGROUP_ACT_OFF.getMessage());
        }
    }

    /**
     * 광고삭제
     * 광고그룹 활성 여부 변경(체크박스) - [광고관리]
     */
    @Transactional
    public ResponseDto<String> updateAgActYns(DeleteAgroupListRequestDto requestDtos, HttpServletRequest servletRequest) {
        List<DeleteAgroupRequestDto> deleteAgList = requestDtos.getDeleteGroupList();
        if(deleteAgList.isEmpty()) {
            return ResponseDto.fail(ErrorCode.NOT_SELECTED_ADGROUP.getCode(), ErrorCode.NOT_SELECTED_ADGROUP.getMessage());
        }

        for (int i = 0; i < deleteAgList.size(); i++) {
            Agroup agroup = validation.isPresentAgroup(deleteAgList.get(i).getAgroupId());
            Ad ad = adRepository.findByAgroup(agroup);
            ad.updateOffAdUseConfig();
            ad.updateOffAdActYn();
            agroup.updateOffAgActYn();
        }
        return ResponseDto.success(ErrorCode.DELETE_ADGROUP.getMessage());
    }
    /** 그룹명 변경 - [광고관리] */
    @Transactional
    public ResponseDto<String> updateAdGroupName(UpdateAgroupNameRequestDto requestDto, HttpServletRequest servletRequest) {
        if(requestDto.getNewAgroupName().isEmpty() || requestDto.getNewAgroupName().equals(" ")) {
            return ResponseDto.fail(ErrorCode.NO_HAVE_AD_GROUP.getCode(),ErrorCode.NO_HAVE_AD_GROUP.getMessage());
        }
        Agroup agroup = agroupRepository.findByAgroupName(requestDto.getAgroupName());

        if(agroupRepository.existsByAgroupName(requestDto.getNewAgroupName())) {
            return ResponseDto.fail(ErrorCode.EXIST_AD_GROUP.getCode(),ErrorCode.EXIST_AD_GROUP.getMessage());
        }

        agroup.updateAgroupName(requestDto);
        return ResponseDto.success(ErrorCode.CHANGE_AD_GROUP.getMessage());
    }
}
