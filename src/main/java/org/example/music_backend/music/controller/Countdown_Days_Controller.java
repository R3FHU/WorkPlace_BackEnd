package org.example.music_backend.music.controller;

import org.example.music_backend.music.Response.Response;
import org.example.music_backend.music.model.Countdown_Days;
import org.example.music_backend.music.service.Countdown_Days_Service;
import org.example.music_backend.music.service.Music_Search_Service;
import org.example.music_backend.music.status.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class Countdown_Days_Controller {
    private final Countdown_Days_Service countdown_Days_Service;
    @Autowired
    public Countdown_Days_Controller(Countdown_Days_Service countdown_Days_Service) {
        this.countdown_Days_Service = countdown_Days_Service;
    }
    @PostMapping("/countdowndays/save")
    public Response<String> countdownDaysSave(@RequestParam LocalDateTime date, @RequestParam String title) {
        try{
            countdown_Days_Service.Save_Countdown_Days(title,date);
            return new Response<>(ResponseStatus.SUCCESS,"成功啦！",countdown_Days_Service.Save_Countdown_Days(title,date));
        }catch (Exception e){
            return new Response<>(ResponseStatus.INTERNAL_ERROR,e.getMessage(),null);
        }
    }
    @GetMapping("/countdowndays/get")
    public Response<LocalDateTime> countdownDaysSave(@RequestParam String id) {
        try{
            if(countdown_Days_Service.Get_Countdown_Days(id)==null){
                return new Response<>(ResponseStatus.NOT_FOUND,"未找到对应记录",null);
            }
            return new Response<>(ResponseStatus.SUCCESS,"成功啦！",countdown_Days_Service.Get_Countdown_Days(id));
        }catch (Exception e){
            return new Response<>(ResponseStatus.INTERNAL_ERROR,e.getMessage(),null);
        }
    }
}
