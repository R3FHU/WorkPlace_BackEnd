package org.example.music_backend.music.controller;

import org.example.music_backend.music.Response.Response;
import org.example.music_backend.music.model.Schedule;
import org.example.music_backend.music.service.Schedule_List_Service;
import org.example.music_backend.music.status.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Schedule_List_Controller {
    private final Schedule_List_Service service;
    @Autowired
    public Schedule_List_Controller(Schedule_List_Service service) {
        this.service = service;
    }
    @GetMapping("/schedule/list")
    public Response <List<Schedule>> Get_Schedule_List(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        try{
            return new Response<>(ResponseStatus.SUCCESS,"成功啦",service.Get_Schedule_List(page,size)) ;
        }catch(Exception e){
           return new Response<>(ResponseStatus.INTERNAL_ERROR,e.getMessage(),null);
        }
    }
    @GetMapping("/schedule/count")
    public Response<Integer> Get_Schedule_Count() {
        try{
            return new Response<>(ResponseStatus.BAD_REQUEST,"成功啦",service.Get_Schedule_List_Count()) ;
        }catch(Exception e){
            return new Response<>(ResponseStatus.INTERNAL_ERROR,e.getMessage(),null);
        }
    }
}
