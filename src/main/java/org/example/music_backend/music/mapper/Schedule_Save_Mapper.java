package org.example.music_backend.music.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface Schedule_Save_Mapper {
    int Schedule_Save(@Param("name") String name, @Param("date") LocalDateTime date, @Param("schedule") String schedule, @Param("id") String id);
}
