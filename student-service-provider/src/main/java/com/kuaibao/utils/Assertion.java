package com.kuaibao.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Objects;

public class Assertion {
    public static void hasText(String text , String message){
        if(StringUtils.isEmpty(text))
            throw new RuntimeException(message);
    }

    public static void notNull(Object obj , String message){
        if(Objects.isNull(obj))
            throw new RuntimeException(message);
    }

    public static void throwError(BindingResult result){
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                throw new RuntimeException(error.getDefaultMessage());
            }
        }
    }

    public static void error(String message){
        throw new RuntimeException(message);
    }
}
