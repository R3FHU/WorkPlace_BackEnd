package org.example.music_backend.music.service;
import org.example.music_backend.music.mapper.Music_Mapper;
import org.example.music_backend.music.model.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Music_ServiceImpl implements Music_Service {
    @Autowired
    private Music_Mapper music_mapper;

    @Override
    public String Get_URL(String name) {
        return music_mapper.Get_URL(name);
    }

}
