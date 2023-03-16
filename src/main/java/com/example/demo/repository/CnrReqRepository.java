package com.example.demo.repository;

import com.example.demo.entity.CnrReq;
import com.example.demo.entity.DadDet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CnrReqRepository extends JpaRepository<CnrReq, Long> {
    Optional<CnrReq> findByDadDet(DadDet dadDet);
}
