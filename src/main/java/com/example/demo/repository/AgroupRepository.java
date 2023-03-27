package com.example.demo.repository;

import com.example.demo.entity.Agroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgroupRepository extends JpaRepository<Agroup, Long> {
    boolean existsByAgroupName(String agroupName);
    Agroup findByAgroupName(String agroupName);
    List<Agroup> findAgroupByAgroupActYnLikeOrderByRegTime(Integer agroupActYn);

}
