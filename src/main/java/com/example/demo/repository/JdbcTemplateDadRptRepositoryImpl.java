package com.example.demo.repository;

import com.example.demo.controller.response.dadRpt.DadRptDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTemplateDadRptRepositoryImpl implements JdbcTemplateDadRptRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateDadRptRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DadRptDto> getDadRptDetail(Long dadDetId) {
        String sql = "SELECT IFNULL(basic_date, '-') AS basic_date, " +
                "dad_det_id, " +
                "SUM(imp_cnt) AS imp_cnt, " +
                "SUM(click_cnt) AS click_cnt, " +
                "ROUND(AVG(avg_imp_rank),1) AS avg_imp_rank, " +
                "TRUNCATE(AVG(avg_cpc),0) AS avg_cpc, " +
                "SUM(ad_spend) AS ad_spend, " +
                "ROUND((SUM(click_cnt)/SUM(imp_cnt))*100,1) AS click_percent " +
                "FROM dad_rpt " +
                "WHERE dad_det_id = ? " +
                "GROUP BY dad_det_id, basic_date WITH ROLLUP " +
                "HAVING dad_det_id IS NOT NULL";

        return jdbcTemplate.query(sql, new Object[]{dadDetId}, new BeanPropertyRowMapper<>(DadRptDto.class));
    }
}
