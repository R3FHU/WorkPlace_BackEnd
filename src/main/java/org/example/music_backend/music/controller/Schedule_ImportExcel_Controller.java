package org.example.music_backend.music.controller;

import com.monitorjbl.xlsx.StreamingReader;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.music_backend.music.Response.Response;
import org.example.music_backend.music.model.Schedule;
import org.example.music_backend.music.status.ResponseStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.example.music_backend.music.utils.Excel_Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Schedule_ImportExcel_Controller {
    @PostMapping ("/schedule/importexcel")
    public Response<List<Map<String, Object>>> uploadExcel(@RequestPart("file") MultipartFile file)  {
        // 检查文件是否为空
        if (file.isEmpty()) {
            return new Response<>(ResponseStatus.INTERNAL_ERROR,"文件不能为空",null);
        }

        // 检查文件类型
        String fileName = file.getOriginalFilename();
        if (fileName != null && !fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
            return new Response<>(ResponseStatus.BAD_REQUEST, "目前只支持xlsx或xls类型文件", null);
        }
        Excel_Utils excel_utils = new Excel_Utils();
        List<Map<String, Object>> result = new ArrayList<>();
        try {
             result = excel_utils.ExcelToList(file);
             if (result == null || result.isEmpty()) {
                 return new Response<>(ResponseStatus.BAD_REQUEST,"处理文件时出错",null);
             }
        }catch(Exception e) {
            return new Response<>(ResponseStatus.INTERNAL_ERROR,"处理文件时出现错误",null);
        }
        return new Response<>(ResponseStatus.SUCCESS,"成功啦",result);
    }

}
