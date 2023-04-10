package com.example.demo.batch.scheduled;

import com.example.demo.batch.BatchConfig;
import com.example.demo.entity.TaskReq;
import com.example.demo.entity.enm.TaskStatus;
import com.example.demo.repository.TaskReqRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobScheduler {

    private final JobLauncher jobLauncher;
    private final BatchConfig batchConfig;
    private final TaskReqRepository taskReqRepository;

    @SneakyThrows
    //프로그램이 시작된 후 5분 후에 jobSchedule() 메소드가 최초로 호출되며, 이후에는 3분마다 주기적으로 호출

    @Scheduled(cron = "0 * * * * ?") // 1분 마다
//    @Scheduled(cron = "0 0/3 * * * ?") // 3분마다
//    @Scheduled(cron = "0 0 0 * * *") // 매일 자정
    public void jobSchedule() {

        // 요청상태가 REQ인 파일
        List<TaskReq> taskReqList = taskReqRepository.findByTaskStatus(TaskStatus.REQ.name());
        if (taskReqList.isEmpty() || taskReqList.size() == 0) {
            return;
        }

        try {
            for (int i = 0; i < taskReqList.size(); i++) {
                Map<String, JobParameter> confMap = new HashMap<>();
                confMap.put("filePath", new JobParameter(taskReqList.get(i).getTaskReqFilePath()));
                confMap.put("time", new JobParameter(System.currentTimeMillis()));
                JobParameters jobParameters = new JobParameters(confMap);
                jobLauncher.run(batchConfig.taskFileJob(), jobParameters);
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
