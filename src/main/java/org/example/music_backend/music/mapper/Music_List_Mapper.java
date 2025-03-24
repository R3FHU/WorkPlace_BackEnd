package org.example.music_backend.music.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.music_backend.music.model.Music;

import java.util.List;

@Mapper
public interface Music_List_Mapper {
    List<Music>Get_Music_List(@Param("offset") int offset, @Param("size") int size);
    int Get_Music_List_Count();
}
