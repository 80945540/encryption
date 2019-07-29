package util;

import java.util.Base64;

/*
* lance
* Base64 编码解码解析
* */
public class Base64Util {

    public static void main(String[] args) {
        //字符串编码
        System.out.println("编码结果:"+encrypt("hello word"));
        //字符串解码
        System.out.println("编码结果:"+decrypt("aGVsbG8gd29yZA=="));
    }

    public static String encrypt(String str){
        byte[] bytes=str.getBytes();
        bytes=Base64.getEncoder().encode(bytes);
        return new String(bytes);
    }

    public static String decrypt(String str){
        byte[] bytes=str.getBytes();
        bytes=Base64.getDecoder().decode(bytes);
        return new String(bytes);
    }
}
