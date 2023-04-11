package com.example.demo.repository;

import com.example.demo.controller.response.dadRpt.DadRptDto;
import com.example.demo.entity.DadRpt;
import com.example.demo.entity.RptCompositeKey;
import com.querydsl.core.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DadRptRepository extends JpaRepository<DadRpt, RptCompositeKey> {
//    void findDad(Long dadDetId);

//    @Query(nativeQuery = true,
//            value = "SELECT " +
//                    "basic_date, " +
//                    "dad_det_id, " +
//                    "SUM(imp_cnt) AS imp_cnt, " +
//                    "SUM(click_cnt) AS click_cnt, " +
//                    "ROUND(AVG(avg_imp_rank),1) AS avg_imp_rank, " +
//                    "TRUNCATE(AVG(avg_cpc),0) AS avg_cpc, " +
//                    "SUM(ad_spend) AS ad_spend, " +
//                    "ROUND((SUM(click_cnt)/SUM(imp_cnt))*100,1) AS click_percent " +
//                    "FROM dad_rpt " +
//                    "WHERE dad_det_id = :dadDetId " +
//                    "GROUP BY dad_det_id, basic_date, adv_id WITH ROLLUP " +
//                    "HAVING dad_det_id IS NOT NULL")
//    List<DadRptDto> getDadRptDetail(@Param("dadDetId")Long dadDetId);



}


