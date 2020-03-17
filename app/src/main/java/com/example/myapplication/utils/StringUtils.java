package com.example.myapplication.utils;

public class StringUtils {

    public static String splitArray(int[] array){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<array.length; i++){
            sb.append(array[i]);
            if(i<array.length-1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

}
