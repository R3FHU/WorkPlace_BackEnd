package org.example.music_backend.music.service;

import org.example.music_backend.music.mapper.Schedule_List_Mapper;
import org.example.music_backend.music.mapper.Schedule_Save_Mapper;
import org.example.music_backend.music.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Schedule_List_ServiceImpl implements Schedule_List_Service {
    @Autowired
    private Schedule_List_Mapper schedule_List_Mapper;
    @Override
    public List<Schedule> Get_Schedule_List(int offset,int size){
        return schedule_List_Mapper.Get_Schedule_List(offset,size);
    }
    public int Get_Schedule_List_Count() {
        return schedule_List_Mapper.Get_Schedule_List_Count();
    }
}
