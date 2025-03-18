package org.example.music_backend.music.controller;

import org.example.music_backend.music.Response.Response;
import org.example.music_backend.music.model.Schedule;
import org.example.music_backend.music.service.Schedule_Save_Service;
import org.example.music_backend.music.status.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@RestController
public class Schedule_Save_Controller {
    @Autowired
    private Schedule_Save_Service schedule_save_service;

    @PostMapping("/schedule/save")
    public Response<String> Schedule_Save(@RequestBody Schedule schedule) {
        String name = schedule.getName();
        LocalDateTime date = schedule.getDate();
        String content = schedule.getSchedule();
        String font = schedule.getFont();
        String size = schedule.getSize();
        String position = schedule.getPosition();
        Boolean isbold = schedule.getIsbold();
        Boolean isitalic = schedule.getIsitalic();
        String color = schedule.getColor();
        try{
            schedule_save_service.Schedule_Save(name, date, content,font,size,position,isbold,isitalic,color);
        return new Response<>(ResponseStatus.SUCCESS,"成功啦！",schedule.getId());
        }catch(Exception e){
            return new Response<>(ResponseStatus.INTERNAL_ERROR,e.getMessage(),null);
        }
    }
}
