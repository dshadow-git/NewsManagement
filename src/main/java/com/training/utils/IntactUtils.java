package com.training.utils;

public class IntactUtils {

    //判断所有数据是否都不为空
    public static boolean isIntact(Object... objects){

        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }
}
