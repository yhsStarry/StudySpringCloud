package com.yhs.order.utils;

import java.util.Random;

public class KeyUtil {
    //设置主键自动增长 因为为Varchar类型 生成唯一的主键
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 1000000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
