package org.example.music_backend.music.service;

import org.example.music_backend.music.mapper.Music_Mapper;
import org.example.music_backend.music.mapper.Music_Search_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Music_Search_ServiceImpl implements Music_Search_Service {
    @Autowired
    private Music_Search_Mapper music_search_mapper;
    @Override
    public List<String> Music_Search(String name){
        return music_search_mapper.Music_Search(name);
    }
}
