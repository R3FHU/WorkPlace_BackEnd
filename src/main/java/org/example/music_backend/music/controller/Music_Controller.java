package org.example.music_backend.music.controller;
import org.example.music_backend.music.model.Music;
import org.example.music_backend.music.service.Music_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Music_Controller {
    private final Music_Service music_service;

    @Autowired
    public Music_Controller(Music_Service music_service) {
        this.music_service = music_service;
    }

    @GetMapping("/music/play")
    public ResponseEntity<String> getMusicURL(@RequestParam String name) {
        try {
            // 调用 Service 方法获取 URL
            String url = music_service.Get_URL(name);
            if (url == null || url.isEmpty()) {
                // 如果未找到歌曲，返回 404 Not Found
                return ResponseEntity.notFound().build();
            }
            // 返回歌曲 URL
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            // 捕获异常并返回 500 Internal Server Error
            return ResponseEntity.status(500).body("出错啦：" + e.getMessage());
        }
    }
}
