package com.letseat.utils;

import com.letseat.enums.StatusCodeEnum;

/**
 * @author zpc
 * @date 2018/10/19 15:13
 */
public class EnumUtil {
    public  static <T extends StatusCodeEnum> T getByStatusCode(Integer statusCode,Class<T> enumClass){
        for(T each:enumClass.getEnumConstants()){
            if(statusCode.equals(each.getStatusCode())){
                return each;
            }
        }
        return null;
    }
}
