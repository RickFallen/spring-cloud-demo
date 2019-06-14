package com.kuaibao.utils;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@ApiModel
public class Response<T> {
    @Setter
    @Getter
    @ApiModelProperty(value = "数据承载" ,dataType = "java.lang.Object")
    private T body;

    @Setter
    @Getter
    @ApiModelProperty(value = "返回消息" , dataType = "java.lang.String")
    private String message;

    @Setter
    @Getter
    @ApiModelProperty(value = "响应状态" , dataType = "java.lang.Integer")
    private Integer status;

    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private final String DEFAULT_SUCCESS_MESSAGE = "成功";
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
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
