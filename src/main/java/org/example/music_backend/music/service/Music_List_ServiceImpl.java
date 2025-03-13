package org.example.music_backend.music.service;

import org.example.music_backend.music.mapper.Music_List_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Music_List_ServiceImpl implements Music_List_Service {
    @Autowired
    private Music_List_Mapper music_List_Mapper;
    @Override
    public List<String> Get_Music_List(int offset,int size){
        return music_List_Mapper.Get_Music_List(offset,size);
    }
    public int Get_Music_List_Count(){
        return music_List_Mapper.Get_Music_List_Count();
    }
}
