package com.example.demo.repository;

import com.example.demo.controller.request.agroup.SearchAgroupRequestDto;
import com.example.demo.controller.response.agroup.AgroupListResponseDto;
import com.example.demo.controller.response.agroup.QAgroupListResponseDto;
import static com.example.demo.entity.QAgroup.agroup;
import static com.example.demo.entity.QAd.ad;

import com.example.demo.entity.Adv;
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
    public List<AgroupListResponseDto> searchAgroupList(SearchAgroupRequestDto agroupRequestDto, Adv adv) {
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
                        ad.adv.eq(adv),
                        agroup.agroupActYn.eq(1),
                        agroup.agroupName.contains(agroupRequestDto.getAgroupName()))
                .groupBy(agroup.agroupId)
                .fetch();
    }
}
