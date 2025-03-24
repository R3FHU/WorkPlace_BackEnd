package org.example.music_backend.music.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.json.Path;

import java.lang.reflect.Field;
import org.example.music_backend.music.Response.Response;
import org.example.music_backend.music.framework.JsonMapper;
import org.example.music_backend.music.model.Music;
import org.example.music_backend.music.service.Music_Search_Service;
import org.example.music_backend.music.status.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.json.Path2;
import redis.clients.jedis.search.Document;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


@RestController
public class Music_Search_Controller {
    private final Music_Search_Service music_search_service;
    private final JedisPooled jedis; // 通过构造器注入，确保复用连接
    @Autowired
    public Music_Search_Controller(Music_Search_Service music_search_service,JedisPooled jedis) {
        this.music_search_service = music_search_service;
        this.jedis = jedis;
    }
    @GetMapping("/music/search")
    public Response<List<String>> music_search(@RequestParam String name) {
        try{
            if (music_search_service.Music_Search(name)==null){
                return new Response<>(ResponseStatus.BAD_REQUEST,"未找到歌曲",null);
            }
            List<String> result = new ArrayList<>();
            var searchResult = jedis.ftSearch("idx:music", "@name:*"+name+"*");
            if(searchResult.getTotalResults()!=0){
                System.out.println(searchResult);
                for (Document doc : searchResult.getDocuments()) {
                        // 获取 JSON 字符串（存储在 "$" 字段中）
                        String json = doc.getString("$");
                        // 解析 JSON
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(json);
                        // 提取 name 字段
                        String musicname = jsonNode.get("name").asText();
                        result.add(musicname);
            }
                return new Response<>(ResponseStatus.SUCCESS,"命中缓存!",result);
                }
            List<Music> musicList = music_search_service.Music_Search(name);
            String jsonData;
            for (Music music : musicList) {
                jsonData = JsonMapper.toJson(music);
                jedis.jsonSet("music:"+music.getId(), Path2.ROOT_PATH, jsonData);
                result.add(music.getName());
            }
            return new Response<>(ResponseStatus.SUCCESS,"成功啦！",result) ;
        }catch (Exception e){
            return new Response<>(ResponseStatus.INTERNAL_ERROR,e.getMessage(),null);
        }
    }
}
