package org.example.music_backend.music.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Schedule {
    private String id;
    private String name;
    private LocalDateTime date;
    private String schedule;
    private String font;//字体
    private String size;//字号
    private String position;//文本位置
    private Boolean isbold;//是否加粗
    private Boolean isitalic;//是否斜体
    private String color;//颜色
}
