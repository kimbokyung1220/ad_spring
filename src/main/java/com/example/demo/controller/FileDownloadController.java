package com.example.demo.controller;

import com.example.demo.controller.request.FileNameRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class FileDownloadController {
    @Value("${file.absolutePath}")
    String UPLOAD_PATH;

    /**
     * 파일 다운로드
     */
    @PostMapping("/download")
    public void downloadFile(HttpServletResponse response,
                             @RequestBody FileNameRequestDto requestDto) throws Exception {
        String path = UPLOAD_PATH + "\\" + requestDto.getFileName();
        String originFileName = requestDto.getFileName().substring(requestDto.getFileName().indexOf("-") + 1);
        System.out.println("--------------------------------");
        System.out.println(originFileName);

        File file = new File(path);
        // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더

        response.addHeader("Content-Disposition", "attachment;filename=\"" + requestDto.getFileName().substring(requestDto.getFileName().indexOf("-") + 1) + "\"");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + requestDto.getFileName().substring(requestDto.getFileName().indexOf("-") + 1) + "\"");
        response.setContentType("application/octet-stream;charset=utf-8");

        response.setContentType("application/octet-stream");
        response.setContentLength((int)file.length());


        try {
            // 파일 응답 스트림 설정
            OutputStream out = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(file); // 파일 읽어오기

            int read = 0;
            byte[] buffer = new byte[1024];
            // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
            while ((read = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

        } catch (Exception e) {
            throw new Exception("download error");
        }
    }

}
