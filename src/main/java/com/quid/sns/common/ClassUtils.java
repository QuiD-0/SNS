package com.quid.sns.common;

import com.quid.sns.exception.ErrorCode;
import com.quid.sns.exception.SnsApplicationException;

public class ClassUtils {

    public static <T> T castInstance(Object obj, Class<T> clazz) {
        if (clazz.isInstance(obj)) {
            return clazz.cast(obj);
        }
        throw new SnsApplicationException(ErrorCode.ILLEGAL_STATE);
    }
}
