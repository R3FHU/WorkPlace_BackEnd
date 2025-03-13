package org.example.music_backend.music.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Schedule {
    private String id;
    private String name;
    private LocalDateTime date;
    private String schedule;
}
