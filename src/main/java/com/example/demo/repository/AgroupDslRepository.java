package com.example.demo.repository;

import com.example.demo.controller.request.agroup.SearchAgroupRequestDto;
import com.example.demo.controller.response.agroup.AgroupListResponseDto;
import com.example.demo.entity.Adv;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AgroupDslRepository {

    List<AgroupListResponseDto> searchAgroupList(SearchAgroupRequestDto agroupRequestDto, Adv adv);
}
