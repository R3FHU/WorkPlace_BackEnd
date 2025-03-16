package org.example.music_backend.music.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.music_backend.music.Response.Response;
import org.example.music_backend.music.model.Schedule;
import org.example.music_backend.music.status.ResponseStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Schedule_ImportExcel_Controller {
    @PostMapping ("/schedule/importexcel")
    public Response<List<Map<String, Object>>> uploadExcel(@RequestPart("file") MultipartFile file) throws IOException {
        // 检查文件是否为空
        if (file.isEmpty()) {
            return new Response<>(ResponseStatus.INTERNAL_ERROR,"文件不能为空",null);
        }

        // 检查文件类型
        String fileName = file.getOriginalFilename();
        if (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
            return new Response<>(ResponseStatus.BAD_REQUEST,"目前只支持xlsx或xls类型文件",null);
        }

        // 读取 Excel 文件
        List<Map<String, Object>> result = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
            if (sheet == null) {
                return new Response<>(ResponseStatus.BAD_REQUEST,"Excel中没有工作表",null);
            }
            Row headerRow = sheet.getRow(0); // 获取表头
            if (headerRow == null) {
                    return new Response<>(ResponseStatus.NOT_FOUND,"Excel中缺少表头",null);
            }
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头
                Map<String, Object> rowData = new HashMap<>();
                for (Cell cell : row) {
                    String header = headerRow.getCell(cell.getColumnIndex()).getStringCellValue();
                    Object cellValue = getCellValue(cell);
                    rowData.put(header, cellValue);
                }
                result.add(rowData);
            }
        }catch(Exception e) {
            return new Response<>(ResponseStatus.INTERNAL_ERROR,"处理文件时出现错误",null);
        }
        return new Response<>(ResponseStatus.SUCCESS,"成功啦",result);
    }

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return null;
        }
    }
}
