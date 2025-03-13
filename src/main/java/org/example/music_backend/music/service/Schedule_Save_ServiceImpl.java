package org.example.music_backend.music.service;

import org.example.music_backend.music.mapper.Schedule_Save_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class Schedule_Save_ServiceImpl implements Schedule_Save_Service {
    @Autowired
    private Schedule_Save_Mapper schedule_save_mapper;

    @Override
    public void Schedule_Save(String name, LocalDateTime date, String schedule) {
        String id = UUID.randomUUID().toString().replace("-", ""); // 生成UUID
        schedule_save_mapper.Schedule_Save(name, date, schedule, id);
    }
}
