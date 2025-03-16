package org.example.music_backend.music.Response;

import lombok.Getter;
import lombok.Setter;
import org.example.music_backend.music.status.ResponseStatus;

public class Response<T> {
    @Getter
    @Setter
    private ResponseStatus status;
    @Getter
    @Setter
    private String message;
    @Setter
    @Getter
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
