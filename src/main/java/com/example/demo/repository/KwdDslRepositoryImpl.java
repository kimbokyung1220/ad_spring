package com.example.demo.repository;

import com.example.demo.controller.request.kwd.KwdNameRequestDto;
import com.example.demo.controller.response.kwd.KwdDto;

import com.example.demo.controller.response.kwd.QKwdDto;
import com.example.demo.entity.Kwd;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.example.demo.entity.QAd.ad;
import static com.example.demo.entity.QDadDet.dadDet;
import static com.example.demo.entity.QKwd.kwd;
import static com.example.demo.entity.QCnrReq.cnrReq;
import static com.example.demo.entity.QItem.item;


import java.util.List;

@Repository
public class KwdDslRepositoryImpl implements KwdDslRepository {
    private final JPAQueryFactory queryFactory;

    public KwdDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    // 키워드 조회
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
                .where(dadDet.dadActYn.eq(1)
                        .and(kwd.sellPossKwdYn.eq(1))
                        .and(ad.adId.eq(adId))
                        .and(kwd.kwdName.contains(requestDto.getKwdName())))
                .fetch();

    }

    // 검수 키워드 조회
    @Override
    public List<KwdDto> searchIspKwdList(String kwdNameDto) {
        return queryFactory.select(new QKwdDto(
                        kwd.kwdId,
                        kwd.kwdName
                ))
                .from(kwd)
                .where(kwd.manualCnrKwdYn.eq(1)
                        .and(kwd.kwdName.contains(kwdNameDto)))
                .fetch();
    }

    // 광고 검수 대상 리스트
    public List<KwdDto> searchIspAdList(String kwdNameDto) {
        return queryFactory.select(new QKwdDto(
                        dadDet.dadDetId,
                        kwd.kwdId,
                        kwd.kwdName,
                        item.itemId,
                        item.itemName,
                        cnrReq.cnrReqId
                ))
                .from(dadDet).innerJoin(kwd).on(dadDet.kwd.kwdId.eq(kwd.kwdId))
                .innerJoin(cnrReq).on(cnrReq.dadDet.dadDetId.eq(dadDet.dadDetId))
                .innerJoin(ad).on(ad.adId.eq(dadDet.ad.adId))
                .innerJoin(item).on(item.itemId.eq(ad.item.itemId))
                .where(dadDet.dadCnrStatus.eq("REQ")
                        .and(kwd.kwdName.contains(kwdNameDto)))
                .fetch();
    }
}
