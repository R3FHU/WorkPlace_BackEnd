package org.example.music_backend.music.controller;

import org.example.music_backend.music.service.Schedule_Save_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class Schedule_Save_Controller {
    @Autowired
    private Schedule_Save_Service schedule_save_service;

    @PostMapping("/schedule/save")
    public String Schedule_Save(@RequestParam String name, @RequestParam LocalDateTime date, @RequestParam String schedule) {
        //try{
            schedule_save_service.Schedule_Save(name, date, schedule);
        return "Schedule saved successfully!";
        //}catch(Exception e){
           // return "Schedule save failed!";
        //}
    }
}
