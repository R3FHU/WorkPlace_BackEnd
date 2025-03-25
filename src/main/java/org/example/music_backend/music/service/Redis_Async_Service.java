package org.example.music_backend.music.service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPooled;
import org.example.music_backend.music.framework.JsonMapper;
import org.example.music_backend.music.model.Music;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.json.Path2;
import java.util.List;
@Service
public class Redis_Async_Service {
    private final JedisPooled jedis;

    @Autowired
    public Redis_Async_Service(JedisPooled jedisPooled) {
        this.jedis = jedisPooled;
    }

    @Async("taskExecutor")
    public void asyncSaveMusic(List<Music> musicList) {
        try {
            for (Music music : musicList) {
                String jsonData = JsonMapper.toJson(music);
                jedis.jsonSet("music:" + music.getId(), Path2.ROOT_PATH, jsonData);
                jedis.expire("music:" + music.getId(), 86400);
            }
        } catch (Exception e) {
            // 添加异步操作异常处理
            System.err.println("Async cache save failed: " + e.getMessage());
        }
    }

    @Async("taskExecutor")
    public void asyncSaveEmptyResult() {
        try {
            jedis.jsonSet("music:0", Path2.ROOT_PATH, "{\"id\": \"\",\"name\": \"\", \"url\": \"\"}");
            jedis.expire("music:0", 300);
        } catch (Exception e) {
            System.err.println("Async empty cache save failed: " + e.getMessage());
        }
    }
}
