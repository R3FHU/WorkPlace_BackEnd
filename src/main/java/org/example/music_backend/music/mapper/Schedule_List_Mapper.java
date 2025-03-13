package org.example.music_backend.music.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.music_backend.music.model.Schedule;

import java.util.List;

@Mapper
public interface Schedule_List_Mapper {
    List<Schedule> Get_Schedule_List(@Param("offset") int offset, @Param("size") int size);
    int Get_Schedule_List_Count();
}
