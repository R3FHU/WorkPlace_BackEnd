package org.example.music_backend.music.model;
import lombok.Data;
import org.example.music_backend.music.framework.JsonField;
import org.example.music_backend.music.framework.JsonModel;


@Data
@JsonModel
public class Music {
    @JsonField(name= "id")
    private int id;
    @JsonField(name = "name")
    private String name;
    @JsonField(name = "url")
    private String url;


}
