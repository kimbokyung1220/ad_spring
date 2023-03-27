package com.example.demo.repository;

import com.example.demo.controller.request.kwd.KwdNameRequestDto;
import com.example.demo.controller.response.kwd.KwdDto;

import com.example.demo.controller.response.kwd.QKwdDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.example.demo.entity.QAd.ad;
import static com.example.demo.entity.QDadDet.dadDet;
import static com.example.demo.entity.QKwd.kwd;


import java.util.List;

@Repository
public class KwdDslRepositoryImpl implements KwdDslRepository {
    private final JPAQueryFactory queryFactory;

    public KwdDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<KwdDto> searchKwdList(Long adId, KwdNameRequestDto requestDto) {
        return queryFactory.select(new QKwdDto(
                        ad.adId,
                        ad.agroup.agroupId,
                        ad.adUseConfigYn,
                        ad.adActYn,
                        ad.regTime,
                        dadDet.dadDetId,
                        dadDet.dadActYn,
                        dadDet.dadUseConfigYn,
                        kwd.kwdId,
                        kwd.kwdName))
                .from(ad)
                .innerJoin(dadDet).on(ad.adId.eq(dadDet.ad.adId))
                .innerJoin(kwd).on(dadDet.kwd.kwdId.eq(kwd.kwdId))
                .where(ad.adActYn.eq(1)
                        .and(kwd.sellPossKwdYn.eq(1))
                        .and(ad.adId.eq(adId))
                        .and(kwd.kwdName.contains(requestDto.getKwdName())))
                .fetch();

    }
}
