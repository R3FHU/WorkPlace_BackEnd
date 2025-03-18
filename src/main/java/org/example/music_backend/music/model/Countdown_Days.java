package org.example.music_backend.music.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Countdown_Days {
    private String id;
    private String title;
    private LocalDateTime date;
}
