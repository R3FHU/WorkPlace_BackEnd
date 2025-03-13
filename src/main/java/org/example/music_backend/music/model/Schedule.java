package org.example.music_backend.music.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Schedule {
    private int id;
    private String name;
    private Date date;
    private String schedule;
}
