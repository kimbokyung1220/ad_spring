package com.example.demo.repository;

import com.example.demo.entity.Ad;
import com.example.demo.entity.DadDet;
import com.example.demo.entity.Kwd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DadDetRepository extends JpaRepository<DadDet, Long> {
    List<DadDet> findByAd(Ad ad);

    Optional<DadDet> findByDadDetId(Long dadDetId);

    DadDet findByKwdAndAd(Kwd kwd, Ad ad);

    DadDet findByKwd(Kwd kwd);
}