package com.gw.tool.utils;

public class StringUtils {

    public static boolean isNotBlank(String str){
        int length;
        if ((str == null) || ((length = str.length ()) == 0)) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace (str.charAt (i))) {
                return true;
            }
        }
        return false;
    }
}
