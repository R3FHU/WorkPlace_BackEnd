package org.example.music_backend.music.service;

import java.time.LocalDateTime;

public interface Schedule_Save_Service {
    void Schedule_Save(String name, LocalDateTime date, String schedule);
}
