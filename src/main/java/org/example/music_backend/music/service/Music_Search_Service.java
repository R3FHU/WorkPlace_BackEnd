package org.example.music_backend.music.service;

import org.example.music_backend.music.model.Music;
import org.springframework.stereotype.Service;

import java.util.List;

public interface Music_Search_Service {
     List<Music> Music_Search(String name);
}
