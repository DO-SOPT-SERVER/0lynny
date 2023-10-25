package com.example.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"code", "status", "success", "data"})
public class GenericResponse<T> {
    private final int code;
    private final String status;
    private final Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public GenericResponse(int code, String status, Boolean success) {
        this.code = StatusCode.OK.getStatusCode();
        this.status = status;
        this.success = success;
    }

    public GenericResponse(int code, String status, Boolean success, T data) {
        this.code = StatusCode.OK.getStatusCode();
        this.status = status;
        this.success = success;
        this.data = data;
    }
}
