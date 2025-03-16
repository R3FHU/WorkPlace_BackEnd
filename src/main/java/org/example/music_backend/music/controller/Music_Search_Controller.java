package org.example.music_backend.music.controller;

import org.apache.ibatis.annotations.Param;
import org.example.music_backend.music.Response.Response;
import org.example.music_backend.music.service.Music_Search_Service;
import org.example.music_backend.music.status.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Music_Search_Controller {
    private final Music_Search_Service music_search_service;
    @Autowired
    public Music_Search_Controller(Music_Search_Service music_search_service) {
        this.music_search_service = music_search_service;
    }
    @GetMapping("/music/search")
    public Response<List<String>> music_search(@RequestParam String name) {
        try{
            if (music_search_service.Music_Search(name)==null|| music_search_service.Music_Search(name).isEmpty()){
                return new Response<>(ResponseStatus.BAD_REQUEST,"未找到歌曲",null);
            }
            return new Response<>(ResponseStatus.SUCCESS,"成功啦！",music_search_service.Music_Search(name)) ;
        }catch (Exception e){
            return new Response<>(ResponseStatus.INTERNAL_ERROR,e.getMessage(),null);
        }
    }
}
