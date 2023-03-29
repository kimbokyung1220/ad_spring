package com.example.demo.repository;

import com.example.demo.controller.response.dadDet.DadDetDto;
import com.example.demo.controller.response.dadDet.QDadDetDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.demo.entity.QAd.ad;
import static com.example.demo.entity.QCnrReq.cnrReq;
import static com.example.demo.entity.QDadDet.dadDet;
import static com.example.demo.entity.QItem.item;
import static com.example.demo.entity.QKwd.kwd;

@Repository
public class DadDetDslRepositoryImpl implements DadDetDslRepository {
    private final JPAQueryFactory queryFactory;

    public DadDetDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    // 광고 검수 대상 리스트
    @Override
    public List<DadDetDto> searchIspAdList(String kwdNameDto) {
        return queryFactory.select(new QDadDetDto(
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

    @Override
    public DadDetDto ispAdListDetail(Long dadDetId) {
        return queryFactory.select(new QDadDetDto(
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
                .where(dadDet.dadDetId.eq(dadDetId))
                .fetchOne();
    }
}
