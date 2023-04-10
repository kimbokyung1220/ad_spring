package com.example.demo.service;

import com.example.demo.controller.request.TaskReqSaveRequestDto;
import com.example.demo.controller.response.ResponseDto;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadFileService {
    private final TaskReqService taskReqService;
    @Value("${file.absolutePath}")
    String UPLOAD_PATH;

    @Transactional
    public ResponseDto<?> uploadFile(MultipartFile taskFile, TaskReqSaveRequestDto requestDto, HttpServletRequest servletRequest) {

        String fileRealName = taskFile.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length()); // 확장자
		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 ////  새롭게 만들어 준다.
		 */
        String[] uuids = UUID.randomUUID().toString().split("-");
        String saveFileName = uuids[0] + "-" + fileRealName;

//        File saveFile = new File(UPLOAD_PATH + "\\" + saveFileName + fileExtension);
        File saveFile = new File(UPLOAD_PATH + "\\" + saveFileName);  // 적용 후
        try {
            taskFile.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.FAILED_FILE_PATH, ErrorCode.FAILED_FILE_PATH.getMessage());
        }
        return taskReqService.saveTaskReq(saveFileName, requestDto, servletRequest);
    }
}
