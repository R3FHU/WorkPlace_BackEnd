package org.example.music_backend.music.service;

import org.example.music_backend.music.model.Schedule;

import java.util.List;

public interface Schedule_List_Service {
    List<Schedule> Get_Schedule_List(int offset, int size);
    int Get_Schedule_List_Count();
}
