package com.example.demo.repository;

import com.example.demo.entity.Kwd;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface KwdRepository extends JpaRepository<Kwd, Long> {

//    String findByKwdName(String kwdName);
    Optional<Kwd> findByKwdName(String kwdName);

    boolean existsByKwdName(String kwdName);
}
