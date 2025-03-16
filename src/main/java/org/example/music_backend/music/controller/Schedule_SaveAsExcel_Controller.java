package org.example.music_backend.music.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.music_backend.music.Response.Response;
import org.example.music_backend.music.model.Schedule;
import org.example.music_backend.music.status.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class Schedule_SaveAsExcel_Controller {
    @PostMapping("/schedule/exportexcel")
    public Response<Integer> exportToExcel(@RequestBody List<Schedule> schedules, HttpServletResponse response) {
        try (Workbook workbook = new XSSFWorkbook();){
            if (schedules == null || schedules.isEmpty()) {
                return new Response<>(ResponseStatus.BAD_REQUEST, "导出的数据不能为空", null);
            }
            // 创建 Excel 工作簿和工作表
            Sheet sheet = workbook.createSheet("日程信息");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("日程标题");
            headerRow.createCell(1).setCellValue("日程日期");
            headerRow.createCell(2).setCellValue("日程内容");


            // 创建数据行
            int rowNum = 1;
            for (Schedule schedule : schedules) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(schedule.getName());
                dataRow.createCell(1).setCellValue(schedule.getDate().toString()); // 转换为字符串
                dataRow.createCell(2).setCellValue(schedule.getSchedule());
            }

            // 设置响应头，触发浏览器下载
            response.setContentType("application/octet-stream");
            String fileName = URLEncoder.encode("日程信息.xlsx", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + fileName);

            // 将 Excel 写入输出流
            workbook.write(response.getOutputStream());
            workbook.close();
            return new Response<>(ResponseStatus.SUCCESS,"成功啦",null);
        }catch (IOException e) {
            return new Response<>(ResponseStatus.INTERNAL_ERROR,e.getMessage(),null);
        }
    }
}
