package org.example.music_backend.music.utils;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.json.Path2;
import redis.clients.jedis.JedisPooled;

import java.util.HashSet;
import java.util.Set;
public class Redis_Search {
    public static Set<String> Redis_JsonSearch(JedisPooled jedis, String searchTerm) {
        Set<String> matchedKeys = new HashSet<>();
        String cursor = "0";
        do {
            var scanResult = jedis.scan(cursor);
            cursor = scanResult.getCursor();

            for (String key : scanResult.getResult()) {
                // 仅匹配存储 JSON 数据的 keys
                if (key.startsWith("user:")) {
                    Object jsonData = jedis.jsonGet(key, Path2.ROOT_PATH);
                    String json = jsonData.toString();
                    if (json != null && json.contains(searchTerm)) {
                        matchedKeys.add(key);
                    }
                }
            }
        } while (!cursor.equals("0"));
        return matchedKeys;
    }
}
