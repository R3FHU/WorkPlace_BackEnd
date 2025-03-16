package org.example.music_backend.music.controller;

import org.example.music_backend.music.service.Music_List_Service;
import org.example.music_backend.music.status.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.music_backend.music.Response.Response;

import java.util.List;

@RestController
public class Music_List_Controller {
    private final Music_List_Service music_List_Service;
    @Autowired
    public Music_List_Controller(Music_List_Service music_List_Service) {
        this.music_List_Service = music_List_Service;
    }
    @GetMapping("/music/list")
    public Response<List<String>> Get_Music_List(@RequestParam(defaultValue = "1") int offset,
                                                      @RequestParam(defaultValue = "10") int size){
        try{
            return new Response<>(ResponseStatus.SUCCESS,"成功啦",music_List_Service.Get_Music_List(offset,size));
        }catch (Exception e){
            return new Response<>(ResponseStatus.INTERNAL_ERROR,e.getMessage(),null);
        }
    }
    @GetMapping("/music/count")
    public Response<Integer> Get_Music_List_Count(){
        try{
            return new Response<>(ResponseStatus.SUCCESS,"成功啦！",music_List_Service.Get_Music_List_Count());
        }catch (Exception e){
            return new Response<>(ResponseStatus.INTERNAL_ERROR,e.getMessage(),null);
        }
    }
}
