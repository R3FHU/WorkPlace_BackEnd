package org.example.music_backend.music.service;

import java.time.LocalDateTime;

public interface Countdown_Days_Service {
    String Save_Countdown_Days(String title,LocalDateTime date);
    LocalDateTime Get_Countdown_Days(String id);

}
