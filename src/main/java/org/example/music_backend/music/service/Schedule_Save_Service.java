package org.example.music_backend.music.service;

import java.sql.Date;

public interface Schedule_Save_Service {
    void Schedule_Save(String name, Date date, String schedule);
}
