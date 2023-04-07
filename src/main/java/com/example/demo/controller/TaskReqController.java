package com.example.demo.controller;

import com.example.demo.controller.request.TaskReqSaveRequestDto;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.controller.response.TaskReqResponseDto;
import com.example.demo.service.TaskReqService;
import com.example.demo.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class TaskReqController {
    @Value("${file.absolutePath}")
    String UPLOAD_PATH;
    private final UploadFileService uploadFileService;
    private final TaskReqService taskReqService;

    /**
     * 작업 요청 내역 전체 조회 - [ 대량 관리 ]
     */
    @GetMapping(value = "/task")
    public ResponseDto<List<TaskReqResponseDto>> getTaskReqAllList() {
        return taskReqService.getTaskReqAllList();
    }

    /**
     * 작업 요청 등록 - [대량 관리]
     */
    @PostMapping(value = "/task/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseDto<?> saveTaskReq(@RequestParam(value = "taskFile") MultipartFile taskFile,
                                      @RequestPart(value = "taskReqDto") TaskReqSaveRequestDto requestDto,
                                      HttpServletRequest servletRequest) {
        return uploadFileService.uploadFile(taskFile, requestDto, servletRequest);
    }
}
