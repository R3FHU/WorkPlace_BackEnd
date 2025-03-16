package org.example.music_backend.music.utils;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Excel_Utils {
    private static final Logger logger = LoggerFactory.getLogger(Excel_Utils.class);
    public List<Map<String, Object>> ExcelToList(MultipartFile file) {
        List<Map<String, Object>> result = new ArrayList<>();
        try (Workbook workbook = StreamingReader.builder().open(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
            if (sheet == null) {
                logger.error("文件中没有工作表");
                return null;
            }
            Row headerRow = sheet.getRow(0); // 获取表头
            if (headerRow == null) {
                logger.error("文件中不存在表头");
                return null;
            }
            for (Row row : sheet) {
                if (row == null||row.getRowNum() == 0) continue; // 跳过表头
                Map<String, Object> rowData = new HashMap<>();
                for (Cell cell : row) {
                    if(cell==null) continue;
                    String header = headerRow.getCell(cell.getColumnIndex()).getStringCellValue();
                    Object cellValue = getCellValue(cell);
                    rowData.put(header, cellValue);
                }
                result.add(rowData);
            }
        }catch(Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return result;
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
