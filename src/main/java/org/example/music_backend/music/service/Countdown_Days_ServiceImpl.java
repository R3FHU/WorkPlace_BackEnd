package org.example.music_backend.music.service;

import org.example.music_backend.music.mapper.Countdown_Days_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class Countdown_Days_ServiceImpl implements Countdown_Days_Service {
    @Autowired
    public Countdown_Days_Mapper countdown_Days_Mapper;
    @Override
    public String Save_Countdown_Days(String title,LocalDateTime date){
        String id = UUID.randomUUID().toString().replace("-", ""); // 生成UUID
        countdown_Days_Mapper.Save_Countdown_Days(id,title,date);
        return id;
    }
    @Override
    public  LocalDateTime Get_Countdown_Days(String id){
        return countdown_Days_Mapper.Get_Countdown_Days(id);
    }
}
