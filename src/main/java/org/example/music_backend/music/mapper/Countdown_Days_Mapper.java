package org.example.music_backend.music.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface Countdown_Days_Mapper {
    void Save_Countdown_Days(@Param("id") String id, @Param("title")String title,@Param("date")LocalDateTime date);
    LocalDateTime Get_Countdown_Days(@Param("id") String id);
}
