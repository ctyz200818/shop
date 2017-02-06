package cn.itcast.shop.utils;

import java.util.UUID;

/**
 * Created by brian on 2017/1/13.
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
