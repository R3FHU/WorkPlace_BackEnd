package org.example.music_backend.music.Response;

import lombok.Getter;
import lombok.Setter;
import org.example.music_backend.music.framework.JsonField;
import org.example.music_backend.music.framework.JsonModel;
import org.example.music_backend.music.status.ResponseStatus;
@JsonModel
public class Response<T> {
    @Getter
    @Setter
    @JsonField
    private ResponseStatus status;
    @Getter
    @Setter
    @JsonField
    private String message;
    @Setter
    @Getter
    @JsonField
    private T data;

    public Response(ResponseStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status.getCode() +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
