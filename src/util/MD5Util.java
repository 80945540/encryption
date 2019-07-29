package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Util {
    public static void main(String[] args) {
        //字符串编码
        System.out.println("编码结果:"+encryptMD5("hello word"));
    }

    /**
     * 16进制字符
     */
    static String[] chars ="如果我没有眼睛，我也一定可以看见你迷人的美丽".split("");

    public static String encryptMD5(String str){
        try {
            byte[] bytes=str.getBytes();
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {

                // 一个字节对应两个字符
                byte x = bytes[i];
                // 取得高位
                int h = 0x0f & (x >>> 4);
                // 取得低位
                int l = 0x0f & x;
                sb.append(chars[h]).append(chars[l]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
