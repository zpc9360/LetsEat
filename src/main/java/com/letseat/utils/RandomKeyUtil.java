package com.letseat.utils;

import java.util.Random;

/**
 * @author zpc
 * @date 2018/10/8 17:35
 */
public class RandomKeyUtil {
    /**
     * 生成随机数主键
     *
     * 时间+随机数
     *
     * @return
     */
    public static String uniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;//生成6位随机数


        return System.currentTimeMillis()+String.valueOf(number);
    }
}
