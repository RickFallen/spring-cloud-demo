package com.kuaibao.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response<T> {
    private T body;
    private String message;
    private Integer status;

    @JsonIgnore
    private final String DEFAULT_SUCCESS_MESSAGE = "成功";
    @JsonIgnore
    private final String DEFAULT_ERROR_MESSAGE = "失败";

    /**
     * 创建Response
     * @param <T>
     * @return
     */
    public static <T> Response<T> of(){
        return new Response();
    }

    public Response success(){
        status = HttpStatus.OK.value();
        message = DEFAULT_SUCCESS_MESSAGE;
        return this;
    }

    public Response success(String message){
        status = HttpStatus.OK.value();
        this.message = message;
        return this;
    }

    public Response error(String message){
        status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
        return this;
    }

    public Response error(){
        status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        message = DEFAULT_ERROR_MESSAGE;
        return this;
    }

    public Response message(String message){
        this.message = message;
        return this;
    }

    public Response body(T t){
        this.body = t;
        return this;
    }

    public Response unAuthorized(){
        this.status = HttpStatus.UNAUTHORIZED.value();
        this.body = null;
        return this;
    }
}
