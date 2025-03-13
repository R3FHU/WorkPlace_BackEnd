package org.example.music_backend.music.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Music_List_Mapper {
    List<String>Get_Music_List(@Param("offset") int offset, @Param("size") int size);
    int Get_Music_List_Count();
}
