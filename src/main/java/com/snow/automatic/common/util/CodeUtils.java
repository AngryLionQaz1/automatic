package com.snow.automatic.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CodeUtils {


    private static Map<String,String> code=new ConcurrentHashMap<>();

    public static void addCode(String k,String v){
        if (code.containsKey(k))code.remove(k);
        code.put(k,v);
    }
    public static void removeCode(String k){
        if (code.containsKey(k))code.remove(k);
    }
    public static boolean checkCode(String k,String v){
        if (!code.containsKey(k))return false;
        if (!v.equals(code.get(k)))return false;
        return true;
    }




}
