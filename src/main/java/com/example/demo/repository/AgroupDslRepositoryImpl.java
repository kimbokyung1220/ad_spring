package com.example.demo.repository;

import com.example.demo.controller.request.agroup.AgroupIdRequestDto;
import com.example.demo.controller.request.agroup.AgroupNameRequestDto;
import com.example.demo.controller.response.agroup.AgroupItemResponseDto;
import com.example.demo.controller.response.agroup.AgroupListResponseDto;
import com.example.demo.controller.response.agroup.QAgroupItemResponseDto;
import com.example.demo.controller.response.agroup.QAgroupListResponseDto;

import static com.example.demo.entity.QAgroup.agroup;
import static com.example.demo.entity.QAd.ad;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AgroupDslRepositoryImpl implements AgroupDslRepository {

    // 엔티티를 관리하는 클래스
    private final JPAQueryFactory queryFactory;

    public AgroupDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AgroupListResponseDto> searchAgroupList(AgroupNameRequestDto agroupRequestDto) {
        return queryFactory.select(new QAgroupListResponseDto(
                        agroup.agroupId,
                        agroup.agroupName,
                        agroup.regTime,
                        agroup.agroupUseConfigYn,
                        ad.adActYn.sum().intValue().coalesce(0).as("adActYn"),
                        ad.adUseConfigYn.sum().intValue().coalesce(0).as("adUseConfigYn")
                ))
                .from(agroup).leftJoin(ad).on(agroup.agroupId.eq(ad.agroup.agroupId))
                .where(
                        agroup.agroupActYn.eq(1),
                        agroup.agroupName.contains(agroupRequestDto.getAgroupName()))
                .groupBy(agroup.agroupId)
                .orderBy(agroup.regTime.asc())
                .fetch();
    }

    @Override
    public AgroupItemResponseDto agroupDetail(AgroupIdRequestDto dto) {
        return queryFactory.select(new QAgroupItemResponseDto(
                agroup.agroupId,
                agroup.agroupName,
                agroup.regTime,
                agroup.agroupUseConfigYn,
                ad.adActYn.sum().intValue().coalesce(0).as("adActYn"),
                ad.adUseConfigYn.sum().intValue().coalesce(0).as("adUseConfigYn")
        ))
                .from(agroup).leftJoin(ad).on(agroup.agroupId.eq(ad.agroup.agroupId))
                .where(
                        agroup.agroupActYn.eq(1),
                        agroup.agroupId.eq(dto.getAgroupId()))
                .groupBy(agroup.agroupId)
                .orderBy(agroup.regTime.asc())
                .fetchOne();
    }
}
