package util;

import java.util.Base64;

public class SecurityUtil {

    public static void main(String[] args) {
        //字符串编码
        System.out.println("编码结果:"+encrypt("hello word"));
        //字符串解码
        System.out.println("编码结果:"+dencrypt("KSFaWC4VMl9GXA=="));
    }


    private final static byte[] ENCRYPT_VAL = {
            65, 68, 54, 52, 65, 53, 69, 48,
            52, 56, 57, 57, 69, 56, 56, 69,
            68, 48, 70, 55, 70, 50, 49, 51,
            65, 52, 68, 69, 54, 65, 53, 48
    };

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] ^ ENCRYPT_VAL[i % ENCRYPT_VAL.length]);
        }
        return new String(Base64.getEncoder().encode(bytes));
    }

    /**
     * 解密
     *
     * @param str
     * @return
     */
    public static String dencrypt(String str) {
        byte[] bytes = Base64.getDecoder().decode(str);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] ^ ENCRYPT_VAL[i % ENCRYPT_VAL.length]);
        }
        return new String(bytes);
    }
}
