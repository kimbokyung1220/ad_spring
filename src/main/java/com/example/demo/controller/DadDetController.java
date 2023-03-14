package com.example.demo.controller;

import com.example.demo.service.DadDetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DadDetController {
    private final DadDetService dadDetService;

}
