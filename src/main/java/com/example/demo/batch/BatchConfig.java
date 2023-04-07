package com.example.demo.batch;

import com.example.demo.batch.chunk.*;
import com.example.demo.batch.dto.DadRptRequestDto;
import com.example.demo.entity.DadRpt;
import com.example.demo.entity.TaskReq;
import com.example.demo.entity.enm.TaskStatus;
import com.example.demo.exception.CustomExceptionHandler;
import com.example.demo.repository.TaskReqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    //  JPA를 사용하기 위해 EntityManagerFactory를 주입받아야 함
    private final EntityManagerFactory entityManagerFactory;

    //private final FlatFileItemReader flatFileItemReader;
    private final TaskFileProcessor taskFileProcessor;
    private final TaskFileWriter taskFileWriter;
//    private final TaskFileLstener taskFileLstener;
    private static final int chunkSize = 1;

    private final TaskReqRepository taskReqRepository;

    @Bean
    public Job taskFileJob() throws Exception {
        return jobBuilderFactory.get("taskFileJob")
//                .incrementer(new RunIdIncrementer())
                .start(taskFileStep1())
                .build();
    }

    @Bean
//    @JobScope
    public Step taskFileStep1() throws Exception {
        System.out.println("*****************");

        return stepBuilderFactory.get("taskFileStep1")
                //<Reader에서 읽어올 타입, Writer에서 넘겨줄 타입>
                .<DadRptRequestDto, DadRpt>chunk(chunkSize)
                .reader(reader(null))
                .processor(taskFileProcessor) // 인스턴스 넘겨주기
                .writer(taskFileWriter)
                .listener(new FlatFileParseExceptionHandler(taskReqRepository)) // FlatFileParseException 처리를 위한 listener 등록
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<DadRptRequestDto> reader(@Value("#{jobParameters[filePath]}") String filePath) {
        TaskReq taskReq = taskReqRepository.findByTaskReqFilePath(filePath);
        taskReqRepository.updateTaskStatusAndStartTime(TaskStatus.ING.name(), LocalDateTime.now(), taskReq.getTaskReqId());

        return new TaskFileReader().reader(filePath);
    }
}
