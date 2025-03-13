package org.example.music_backend.music.service;

import java.util.List;

public interface Music_List_Service {
    List<String> Get_Music_List(int offset, int size);
    int Get_Music_List_Count();

}
