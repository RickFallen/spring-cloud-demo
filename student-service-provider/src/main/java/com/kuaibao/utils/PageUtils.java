package com.kuaibao.utils;

import com.baomidou.mybatisplus.extension.api.R;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public class PageUtils {
    public static <T,R> Function<T,R> getFuncion(Class<R> clazzToConvert) {
        Function<T,R> fun = t -> {
            R r = null;
            try {
                r = clazzToConvert.newInstance();
                BeanUtils.copyProperties(r, t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            return r;
        };
        return fun;
    }
}
