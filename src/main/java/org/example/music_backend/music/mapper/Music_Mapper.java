package org.example.music_backend.music.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Music_Mapper {
    String Get_URL(String name);
}
