package com.kuaibao.utils;

import com.baomidou.mybatisplus.extension.api.R;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public class PageUtils {
    /**
     * @param clazzToConvert
     * @param <T>   源对象
     * @param <R>   目标转换对象
     * @return
     */
    public static <T,R> Function<T,R> getConvertFunction(Class<R> clazzToConvert) {
        Function<T,R> fun = t -> {
            R r;
            try {
                r = clazzToConvert.newInstance();
                BeanUtils.copyProperties(r, t);
            } catch (Exception e) {
                throw new RuntimeException(clazzToConvert.getSimpleName() + "向" + t.getClass().getSimpleName() + "复制属性时异常");
            }
            return r;
        };
        return fun;
    }
}
