package org.example.music_backend.music.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Music_Search_Mapper {
    List<String> Music_Search(String name);
}
