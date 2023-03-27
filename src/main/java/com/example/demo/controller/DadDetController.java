package com.example.demo.controller;

import com.example.demo.controller.request.daddet.DadUseConfigYnListRequestDto;
import com.example.demo.controller.request.daddet.DadUseConfigYnRequestDto;
import com.example.demo.controller.request.daddet.DeleteDadListRequestDto;
import com.example.demo.service.DadDetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DadDetController {
    private final DadDetService dadDetService;

    /**
     * 직접광고 사용 설정 여부
//     */
//    @PostMapping("/dad/aduc")
//    public Long updateDadUseConfig(@RequestBody DadUseConfigYnRequestDto requestDto) {
//        return dadDetService.updateDadUseConfig(requestDto);
//    }

    /**
     * 직접광고 사용 설정 여부(체크박스)
     */
//    @PostMapping("/dad/aducs")
//    public void updateDadUseConfigs(@RequestBody DadUseConfigYnListRequestDto requestDto) {
//        dadDetService.updateDadUseConfigs(requestDto);
//    }
//    /**
//     * 직접광고 활성여부 변경(체크박스) - [광고관리]
//     */
//    @PostMapping("/dad/agayns")
//    public void updateDadActs(@RequestBody DeleteDadListRequestDto requestDto) {
//        dadDetService.updateDadActs(requestDto);
//    }

}
