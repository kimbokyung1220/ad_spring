package com.example.demo.batch;

import com.example.demo.entity.DadRpt;
import com.example.demo.entity.TaskReq;
import com.example.demo.entity.enm.TaskStatus;
import com.example.demo.repository.TaskReqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.core.io.FileSystemResource;

@RequiredArgsConstructor
public class FlatFileParseExceptionHandler {
    private final TaskReqRepository taskReqRepository;

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (stepExecution.getFailureExceptions().stream().anyMatch(ex -> ex instanceof FlatFileParseException)) {
            stepExecution.setStatus(BatchStatus.FAILED);
            stepExecution.getJobExecution().setStatus(BatchStatus.FAILED);
            stepExecution.getJobExecution().setExitStatus(ExitStatus.FAILED.addExitDescription("Flat file parse exception occurred"));
        }
        String filePath = stepExecution.getJobExecution().getJobParameters().getString("filePath");

        TaskReq taskReq = taskReqRepository.findByTaskReqFilePath(filePath);
        taskReqRepository.updateTaskStatus(TaskStatus.ERROR.name(), taskReq.getTaskReqId());
        return ExitStatus.COMPLETED;
    }

    @AfterJob
    public ExitStatus afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            String filePath = jobExecution.getJobParameters().getString("filePath");
            TaskReq taskReq = taskReqRepository.findByTaskReqFilePath(filePath);
            taskReqRepository.updateTaskStatus(TaskStatus.COMPLETED.name(), taskReq.getTaskReqId());
        }
        return ExitStatus.COMPLETED;
    }


}