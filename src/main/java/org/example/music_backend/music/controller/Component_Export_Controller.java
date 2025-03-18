package org.example.music_backend.music.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.music_backend.music.Response.Response;
import org.example.music_backend.music.status.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
@RestController
public class Component_Export_Controller {
    String lineSeparator = System.lineSeparator();
    StringBuilder comment = new StringBuilder();
    @PostMapping("component/export")
    public Response<Integer> Component_Export_Controller(@RequestBody List<Map<String, Integer>> components, HttpServletResponse response) {
        try{
            Integer Serial_Number = 0;
            Integer Exist=0;
            Integer Position_X=0;
            Integer Position_Y=0;
            Integer Size=0;
            for (Map<String, Integer> component : components) {
                Serial_Number = component.get("Serial_Number");
                Exist = component.get("Exist");
                Position_X = component.get("Position_X");
                Position_Y = component.get("Position_Y");
                Size = component.get("Size");
                if (Serial_Number == null || Exist == null || Position_X == null || Position_Y == null || Size == null) {
                    return new Response<>(ResponseStatus.BAD_REQUEST, "缺少必要的字段", 0);
                }
                comment.append("Serial_Number: ").append(Serial_Number).append(" ")
                        .append("Exist: ").append(Exist).append(" ")
                        .append("Position_X: ").append(Position_X).append(" ")
                        .append("Position_Y: ").append(Position_Y).append(" ")
                        .append("Size: ").append(Size).append(lineSeparator);
            }
            byte[] bytes = comment.toString().getBytes();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"设置.workplace\"");
            response.setContentLength(bytes.length);
            try (OutputStream os = response.getOutputStream()) {
                os.write(bytes);
                return new Response<>(ResponseStatus.SUCCESS,"成功啦",1);
            }catch(Exception e){
                return new Response<>(ResponseStatus.BAD_REQUEST,"生成文件时出错",0);
            }

        }catch (Exception e){
            return new Response<>(ResponseStatus.INTERNAL_ERROR,e.getMessage(),0);
        }
    }
}
