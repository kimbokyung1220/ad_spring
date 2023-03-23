package com.example.demo.repository;

import com.example.demo.controller.request.item.ItemNoNameRequestDto;
import com.example.demo.controller.response.item.MngItemListResponseDto;
import com.example.demo.controller.response.item.QMngItemListResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static com.example.demo.entity.QItem.item;
import static com.example.demo.entity.QAd.ad;

import javax.persistence.EntityManager;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class ItemDslRepositoryImpl implements ItemDslRepository {
    // 엔티티를 관리하는 클래스
    private final JPAQueryFactory queryFactory;

    public ItemDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MngItemListResponseDto> searchItemList(Long agroupId, ItemNoNameRequestDto noNameRequestDto) {
        return queryFactory.select(new QMngItemListResponseDto(
                        ad.adId,
                        ad.agroup.agroupId,
                        ad.adv.advId,
                        ad.adUseConfigYn,
                        ad.adActYn,
                        ad.regTime,
                        item.itemId,
                        item.itemNo,
                        item.itemName,
                        item.itemActYn
                ))
                .from(ad)
                .innerJoin(item)
                .on(ad.item.itemId.eq(item.itemId)
                        .and(ad.agroup.agroupId.eq(agroupId)).and(ad.adActYn.eq(1)))
                .where(item.itemNo.contains(noNameRequestDto.getItemNo())
                        .or(item.itemName.contains(noNameRequestDto.getItemName())))
                .fetch();
    }
}
