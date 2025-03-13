package org.example.music_backend.music.model;
import lombok.Data;

import java.sql.Date;

@Data
public class Music {
    private int id;
    private String name;
    private String url;
}
