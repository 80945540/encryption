package util;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtil {

    //必须16位  否则会报错  偏移量
    private static String IV = "asdfgh1234567890";
    //钥匙  必须16位
    private static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCA6exxuVOdLvxb6u+Gf5FvZD6zGO1sqDbW7zBgga/8LVZBktaljn0OzCjQKbfe/AQgyh/nIVi1zFO1+FC9cGSiWPGo1uX9kY5Chm0MS8UhtNfO13sOD0U/47MMOduuQ3WP+EJ0wFRvpzyGAp0vKqfCj3Hyrtaf8o7CqTGoLV7G7QIDAQAB";
    private static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIDp7HG5U50u/Fvq74Z/kW9kPrMY7WyoNtbvMGCBr/wtVkGS1qWOfQ7MKNApt978BCDKH+chWLXMU7X4UL1wZKJY8ajW5f2RjkKGbQxLxSG0187Xew4PRT/jsww5265DdY/4QnTAVG+nPIYCnS8qp8KPcfKu1p/yjsKpMagtXsbtAgMBAAECgYBXf+94BWWmg9TQgvdPYFkTtYQFRj8pCEgovTMl3gDYduFcItHEj6F8oMB3AkoGdSJMK0VaOT0gMG8FTWVoH9h9iSM5uu/wsqr7mFI13+18bGFNv0OotV4UNpvlXqSksoRmetZDtaDPhO9/6anD1zY8VrJMXzJfJXhNdGFArEjpwQJBAOBsvzNDy1w/QtLobDm+TSOVVNtoPMO5qddR5PRnRWtpKASY4dQDl/KPa1fDrNp3oGfqA5FPLrQCmd6502Mvmn0CQQCTDRgarAuH9UVgGDeR+E18lWBsZEM4mGHFi9aigeLEw0th8PqZpdrPJixSo4S2lKcopkkagljiYONLlVeznBkxAkEAvRvloZUm73x/GqmvSJkK90kGUDvtuB/i9gWUID5FSNU7W2RYJwdAKqyfjzzbktvq1qVijDdk61qlvgBoF9QtIQJAcIammVJqCIHxspUVgQfHE7yi6o7Wuaoxtx9JAVXvF65yMuJagdTe2YFWjW4/kg+y0nJcooJ4TdLiW+ZOFE0xIQJBAILYIuxY5OpAm8cMqLOtldczj4sFfZb7hhojcEm3+/0ezNhzhZWPMI6txFieDel40a/qdPr8EPZjc0uYKTtlKNc=";

    public static void main(String[] args) {
//        genKeyPair();
        //字符串编码
        System.out.println("编码结果:" + encrypt("hello world", PUBLIC_KEY));

        //字符串解码
        System.out.println("编码结果:" + decrypt("NEo+98UAvp0q69M1mxorD/zrL4QjhBGAFd2Wj2nGcsF8Txb9r8VnUvFqGUM7ZNwXZdcnzW/SZqbGc8rHT0mmtPGWMlzJV8jMAWUgOh9jozyablwWU8Uj4BItiderZSQTrD+3E+ZL5TkkQKwmNCmdaABmozpkUz4B893m3GzhpjQ=",PRIVATE_KEY));

        long startTime=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            encrypt("hello world", PUBLIC_KEY);
        }
        System.out.println("RSA加密10000次耗时:" + (System.currentTimeMillis()-startTime));

        long startTime1=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            decrypt("NEo+98UAvp0q69M1mxorD/zrL4QjhBGAFd2Wj2nGcsF8Txb9r8VnUvFqGUM7ZNwXZdcnzW/SZqbGc8rHT0mmtPGWMlzJV8jMAWUgOh9jozyablwWU8Uj4BItiderZSQTrD+3E+ZL5TkkQKwmNCmdaABmozpkUz4B893m3GzhpjQ=",PRIVATE_KEY);
        }
        System.out.println("RSA解密10000次耗时:" + (System.currentTimeMillis()-startTime1));
    }

    /**
     * 随机生成密钥对
     */
    public static void genKeyPair() {
        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            // 初始化密钥对生成器，密钥大小为96-1024位
            keyPairGen.initialize(1024);
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            // 得到公钥字符串
            String publicKeyString = new String(Base64.getEncoder().encode(publicKey.getEncoded()));
            // 得到私钥字符串
            String privateKeyString = new String(Base64.getEncoder().encode((privateKey.getEncoded())));
            // 打印公钥和私钥
            System.out.println("公匙:" + publicKeyString);
            System.out.println("私匙:" + privateKeyString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     */
    public static String encrypt(String str, String publicKey) {
        try {
            byte[] bytes = str.getBytes();
            //获得RSA公匙
            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey.getBytes())));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            bytes = cipher.doFinal(bytes);
            return new String(Base64.getEncoder().encode(bytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解密
     */
    public static String decrypt(String str, String privateKey) {
        try {
            byte[] bytes = Base64.getDecoder().decode(str);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey.getBytes())));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            bytes = cipher.doFinal(bytes);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
