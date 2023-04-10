//package com.example.demo.batch.chunk;
//
//import com.example.demo.entity.TaskReq;
//import com.example.demo.entity.enm.TaskStatus;
//import com.example.demo.repository.TaskReqRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.annotation.BeforeStep;
//import org.springframework.batch.item.ExecutionContext;
//import org.springframework.batch.item.file.MultiResourceItemReader;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.net.MalformedURLException;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RequiredArgsConstructor
//public class TaskFileLstener {
//    @Value("${file.absolutePath}")
//    String PATH;
//    private final TaskReqRepository taskReqRepository;
//
//    @BeforeStep
//    public void beforeStep(StepExecution stepExecution) throws MalformedURLException {
//        List<TaskReq> taskReqList = taskReqRepository.findByTaskStatus(TaskStatus.REQ.name());
//        if (taskReqList.size() == 0) {
//            return;
//        }
//
//        System.out.println("실행");
////        Resource[] resources = new Resource[taskReqList.size()];
////        for (int i = 0; i < taskReqList.size(); i++) {
////            TaskReq taskReq = taskReqList.get(i);
////            String fileName =  taskReq.getTaskReqFilePath().substring(10);
////            String filePath =  taskReq.getTaskReqFilePath();
////            resources[i] = new FileSystemResource(PATH + "\\" + fileName );
////        }
////        multiResourceItemReader.setResources(resources);
//
//        // 상태, 시작시간 UPDATE
//        taskStatusUpdate(taskReqList);
//
//    }
//
//    public void taskStatusUpdate(List<TaskReq> taskReqList) {
//        for (int i = 0; i < taskReqList.size(); i++) {
//            TaskReq taskReq = taskReqRepository.findByTaskReqId(taskReqList.get(i).getTaskReqId());
//            taskReqRepository.updateTaskStatusAndStartTime(TaskStatus.ING.name(), LocalDateTime.now(), taskReq.getTaskReqId());
//        }
//    }
//
//}
