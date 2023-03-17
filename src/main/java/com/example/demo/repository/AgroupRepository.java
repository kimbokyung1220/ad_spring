package com.example.demo.repository;

import com.example.demo.entity.Adv;
import com.example.demo.entity.Agroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgroupRepository extends JpaRepository<Agroup, Long> {
    List<Agroup> findByAdv(Adv adv);
    boolean existsByAgroupName(String agroupName);
    Agroup findByAgroupName(String agroupName);
}
