package com.example.demo.service;

import com.example.demo.controller.request.TaskReqSaveRequestDto;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.controller.response.TaskReqResponseDto;
import com.example.demo.entity.Member;
import com.example.demo.entity.TaskReq;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.TaskReqRepository;
import com.example.demo.service.common.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskReqService {
    @Value("${file.absolutePath}")
    String UPLOAD_PATH;
    private final ValidationService validation;
    private final TaskReqRepository taskReqRepository;

    /**
     * 작업 요청 내역 전체 조회 - [ 대량 관리 ]
     */
    public ResponseDto<List<TaskReqResponseDto>> getTaskReqAllList() {
        List<TaskReqResponseDto> taskReqList = taskReqRepository.findAllByOrderByTaskReqTime()
                .stream()
                .map(taskReq -> TaskReqResponseDto.of(taskReq))
                .collect(Collectors.toList());

        return ResponseDto.success(taskReqList);
    }

    /**
     * 작업 요청 등록 - [대량 관리]
     */
    public ResponseDto<?> saveTaskReq(String saveFileName,
                                      TaskReqSaveRequestDto requestDto,
                                      HttpServletRequest servletRequest) {

        if(requestDto.getTaskName().equals("") || requestDto.getTaskName().equals(" ") || requestDto.getTaskName().equals(null)) {
            throw new CustomException(ErrorCode.EMPTY_TASK_NAME);
        }

        Member member = validation.getMember(servletRequest);
        String taskFilePath = saveFileName;
        TaskReq taskReq = requestDto.saveTaskReq(member, taskFilePath);
        taskReqRepository.save(taskReq);

        return getTaskReqAllList();
    }
}
