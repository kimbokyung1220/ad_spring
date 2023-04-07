package com.example.demo.repository;

import com.example.demo.controller.response.QTaskReqDto;
import com.example.demo.controller.response.dadDet.DadDetDto;
import com.example.demo.controller.response.dadDet.QDadDetDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.entity.QAd.ad;
import static com.example.demo.entity.QAgroup.agroup;
import static com.example.demo.entity.QCnrReq.cnrReq;
import static com.example.demo.entity.QDadDet.dadDet;
import static com.example.demo.entity.QItem.item;
import static com.example.demo.entity.QKwd.kwd;

@Repository
public class TaskReqDslRepositoryImpl implements TaskReqDslRepository {
    private final JPAQueryFactory queryFactory;

    public TaskReqDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public void updateTaskStatusAndStartTime(String status, LocalDateTime taskStartTime) {
//        return queryFactory.select
//        queryFactory.update(new QTaskReqDto(
//
//        ))
    }

    // 광고 검수 대상 리스트

}
