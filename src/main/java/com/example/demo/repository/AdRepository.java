package com.example.demo.repository;

import com.example.demo.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Optional<Ad> findByAdId(Long id);
}
