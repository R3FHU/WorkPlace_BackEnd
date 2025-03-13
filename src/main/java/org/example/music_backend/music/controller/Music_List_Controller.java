package org.example.music_backend.music.controller;

import org.example.music_backend.music.service.Music_List_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Music_List_Controller {
    private final Music_List_Service music_List_Service;
    @Autowired
    public Music_List_Controller(Music_List_Service music_List_Service) {
        this.music_List_Service = music_List_Service;
    }
    @GetMapping("/music/list")
    public List<String> Get_Music_List(@RequestParam(defaultValue = "1") int offset,
                                       @RequestParam(defaultValue = "10") int size){
        try{
            return music_List_Service.Get_Music_List(offset, size);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/music/count")
    public int Get_Music_List_Count(){
        try{
            return music_List_Service.Get_Music_List_Count();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
