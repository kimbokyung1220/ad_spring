package com.example.demo.repository;

import com.example.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 전체조회
    List<Item> findAllByOrderByItemNoAsc();
    // 상품명 + 상품번호 조회
    List<Item> findByItemNameContainingAndItemNoContainingOrderByItemNoAsc(String itemName, String itemNo);
    // 상품명 조회
    List<Item> findByItemNameContainingOrderByItemNoAsc(String itemName);
    // 상품번호 조회
    List<Item> findByItemNoContainingOrderByItemNoAsc(String itemNo);
}
