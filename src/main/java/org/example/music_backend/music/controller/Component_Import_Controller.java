package org.example.music_backend.music.controller;

import org.example.music_backend.music.Response.Response;
import org.example.music_backend.music.status.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class Component_Import_Controller {
    @PostMapping("/component/export")
    public Response<List<Map<String,Integer>>> exportComponent(@RequestPart("file") MultipartFile File) {
        if (File.isEmpty()) {
            return new Response<>(ResponseStatus.BAD_REQUEST,"文件不能为空！",null);
        }
        String FileName = File.getOriginalFilename();
        if (FileName != null&&!FileName.endsWith("workplace")){
            return new Response<>(ResponseStatus.BAD_REQUEST,"目前只支持.workplace类型的文件",null);
        }
        List<Map<String,Integer>> components = new ArrayList<>();
        final Pattern LINE_PATTERN = Pattern.compile(
                "^\\s*Serial_Number:\\s*(\\d+)\\s+" +  // 匹配 Serial_Number
                        "Exist:\\s*(\\d+)\\s+" +               // 匹配 Exist
                        "Position_X:\\s*(\\d+)\\s+" +          // 匹配 Position_X
                        "Position_Y:\\s*(\\d+)\\s+" +          // 匹配 Position_Y
                        "Size:\\s*(\\d+)\\s*$"                 // 匹配 Size
        );
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(File.getInputStream()))){
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String trimmedLine = line.trim();
                // 跳过空行
                if (trimmedLine.isEmpty()) continue;
                Matcher matcher = LINE_PATTERN.matcher(trimmedLine);
                if (!matcher.matches()) {
                    return new Response<>(
                            ResponseStatus.BAD_REQUEST,
                            "第 " + lineNumber + " 行格式错误: " + line,
                            null
                    );
                }
                try {
                    Map<String, Integer> component = new LinkedHashMap<>();
                    component.put("Serial_Number", Integer.parseInt(matcher.group(1)));
                    component.put("Exist", Integer.parseInt(matcher.group(2)));
                    component.put("Position_X", Integer.parseInt(matcher.group(3)));
                    component.put("Position_Y", Integer.parseInt(matcher.group(4)));
                    component.put("Size", Integer.parseInt(matcher.group(5)));
                    components.add(component);
                } catch (NumberFormatException e) {
                    return new Response<>(
                            ResponseStatus.BAD_REQUEST,
                            "第 " + lineNumber + " 行包含非法数字",
                            null
                    );
                } catch (IndexOutOfBoundsException e) {
                    return new Response<>(
                            ResponseStatus.BAD_REQUEST,
                            "第 " + lineNumber + " 行字段缺失",
                            null
                    );
                }
            }
            return new Response<>(
                    ResponseStatus.SUCCESS,
                    "成功解析 " + components.size() + " 个组件",
                    components
            );


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


