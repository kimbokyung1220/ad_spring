package com.example.demo.repository;

import com.example.demo.controller.request.agroup.SearchAgroupRequestDto;
import com.example.demo.entity.Adv;
import com.example.demo.entity.Agroup;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AgroupRepository extends JpaRepository<Agroup, Long> {
    boolean existsByAgroupName(String agroupName);
    Agroup findByAgroupName(String agroupName);


}
