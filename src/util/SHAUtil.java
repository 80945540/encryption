package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {
    public static void main(String[] args) {
        //字符串编码
        System.out.println("编码结果:"+encryptMD5("hello world"));

        long startTime=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            encryptMD5("hello world");
        }
        System.out.println("AES加密10000次耗时:" + (System.currentTimeMillis()-startTime));

    }

    public static String encryptMD5(String str){
        try {
            byte[] bytes=str.getBytes();
            MessageDigest messageDigest=MessageDigest.getInstance("SHA");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();

            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, bytes).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
