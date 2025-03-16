package org.example.music_backend.music.status;

public enum ResponseStatus {
    SUCCESS(200, "Success"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_ERROR(500, "Internal Server Error"),
    BAD_REQUEST(400,"Bad Request");

    private final int code;
    private final String description;

    ResponseStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
