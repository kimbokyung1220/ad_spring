package com.example.demo.service;

import com.example.demo.repository.TaskReqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class UploadFileService {

    private final TaskReqRepository taskReqRepository;

}
