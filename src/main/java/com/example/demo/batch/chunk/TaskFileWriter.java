package com.example.demo.batch.chunk;

import com.example.demo.entity.DadRpt;
import com.example.demo.repository.DadRptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class TaskFileWriter implements ItemWriter<DadRpt> {
    private final DadRptRepository dadRptRepository;

    @Override
    public void write(List<? extends DadRpt> items) throws Exception {
        System.out.println("********************************************");
        System.out.println(" ===== SPRING BATCH DB 적재 완 =======");
        System.out.println("********************************************");
        dadRptRepository.saveAll(new ArrayList<DadRpt>(items));
    }
}
