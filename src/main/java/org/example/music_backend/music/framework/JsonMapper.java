package org.example.music_backend.music.framework;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.lang.reflect.Field;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.json.Path;

import java.lang.reflect.Field;

// Redis JSON 存储示例
public class JsonMapper {

    public static String toJson(Object obj) throws IllegalAccessException {
        if (!obj.getClass().isAnnotationPresent(JsonModel.class)) {
            throw new IllegalArgumentException("The object is not a JsonModel.");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode(); // 使用 Jackson 创建 JSON 节点
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(JsonField.class)) {
                JsonField jsonField = field.getAnnotation(JsonField.class);
                if (jsonField.ignore()) continue;

                field.setAccessible(true);
                String fieldName = jsonField.name().isEmpty() ? field.getName() : jsonField.name();
                Object fieldValue = field.get(obj);

                // 根据字段类型动态添加到 JSON
                if (fieldValue instanceof Integer) {
                    jsonNode.put(fieldName, (Integer) fieldValue);
                } else if (fieldValue instanceof Boolean) {
                    jsonNode.put(fieldName, (Boolean) fieldValue);
                } else {
                    jsonNode.put(fieldName, fieldValue.toString());
                }
            }
        }

        return jsonNode.toString(); // 返回 JSON 字符串
    }

}
