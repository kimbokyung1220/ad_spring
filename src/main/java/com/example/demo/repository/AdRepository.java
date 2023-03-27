package com.example.demo.repository;

import com.example.demo.entity.Ad;
import com.example.demo.entity.Agroup;
import com.example.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Optional<Ad> findByAdId(Long id);
    boolean existsAdByItemAndAdActYnLike(Item item, Integer adActYn);
    boolean existsAdByAdId(Long adId);

    Ad findByAgroup(Agroup agroup);
}
