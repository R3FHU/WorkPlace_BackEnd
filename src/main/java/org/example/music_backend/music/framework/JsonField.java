package org.example.music_backend.music.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 标记字段为 JSON 属性
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonField {
    String name() default ""; // 自定义 JSON 字段名
    boolean ignore() default false; // 是否忽略该字段
}
