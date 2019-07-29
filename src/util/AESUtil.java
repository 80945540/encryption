package util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    //必须16位  否则会报错  偏移量
    private static String IV ="asdfgh1234567890";
    //钥匙  必须16位
    private static String KEY = "1234567890asdfgh";

    public static void main(String[] args) {
        //字符串编码
        System.out.println("编码结果:" + encrypt("hello word"));
        //字符串解码
        System.out.println("编码结果:" + decrypt("UePyU9tACUkzOEHYFMUsNg=="));

        long startTime=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            encrypt("hello word");
        }
        System.out.println("AES加密10000次耗时:" + (System.currentTimeMillis()-startTime));

        long startTime1=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            decrypt("UePyU9tACUkzOEHYFMUsNg==");
        }
        System.out.println("AES解密10000次耗时:" + (System.currentTimeMillis()-startTime1));
    }

    //加密
    public static String encrypt(String str) {
        try {
            byte[] bytes = str.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes());
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            bytes = cipher.doFinal(bytes);
            bytes = Base64.getEncoder().encode(bytes);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //解密
    public static String decrypt(String str) {
        try {
            byte[] bytes = Base64.getDecoder().decode(str);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes());
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            bytes = cipher.doFinal(bytes);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
