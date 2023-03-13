package com.example.demo.repository;

import com.example.demo.entity.Adv;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.util.Optional;

public interface AdvRepository extends JpaRepository<Adv, String> {
}
