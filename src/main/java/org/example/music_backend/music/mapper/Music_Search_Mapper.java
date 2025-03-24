package org.example.music_backend.music.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.music_backend.music.model.Music;

import java.util.List;

@Mapper
public interface Music_Search_Mapper {
    List<Music> Music_Search(String name);
}
