package util;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DSAUtil {

    private static String PUBLIC_KEY = "MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAMUKDkoben3PPK01uS7hXrxKSOgJpQxVigU7mkfQL87B5GtwUKWmdKM5nMC89avU/S3qTg95+dQKjgydYqe8Ej3SVzDwnGlkZzWGgUOgs0pQ8q/cJ++4eUETH3l4svsLzwDgKIoT3fDBUef3fZ95oWbe1pIIt8RN0KRFJw8U+r4A";
    private static String PRIVATE_KEY = "MIIBSwIBADCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoEFgIUa38CMSvzE1kkWgLiBQDrKCavBEk=";

    public static void main(String[] args) {
        //随机生成成对密钥
        genKeyPair();

        String sign=sign("hello word", PRIVATE_KEY);

        System.out.println("数字签名:" + sign);
        //字符串解码
        System.out.println("数字签名校验:" + verify("hello word",sign,PUBLIC_KEY));

        long startTime=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            sign("hello word", PRIVATE_KEY);
        }
        System.out.println("DSA加密10000次耗时:" + (System.currentTimeMillis()-startTime));

        long startTime1=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            verify("hello word","MC4CFQCMUa+mnODkTClp8moBTvAmrAr3dAIVAIxbfwXF2vFOCua0I17140cNowSp",PUBLIC_KEY);
        }
        System.out.println("DSA解密10000次耗时:" + (System.currentTimeMillis()-startTime1));

    }

    /**
     * 随机生成密钥对
     */
    public static void genKeyPair() {
        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于DSA算法生成对象 可以使用RSA算法生成 成对出现就好
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
            // 初始化密钥对生成器，密钥大小为96-1024位 可自定义随机产生器 SecureRandom
            keyPairGen.initialize(1024);
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到私钥
            DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();
            // 得到公钥
            DSAPublicKey publicKey = (DSAPublicKey) keyPair.getPublic();
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
     * 用私钥对信息生成数字签名
     */
    public static String sign(String str, String privateKey) {
        try {
            byte[] bytes = str.getBytes();
            //获得DSA公匙
            DSAPrivateKey keyFactory = (DSAPrivateKey) KeyFactory.getInstance("DSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey.getBytes())));
            Signature signature=Signature.getInstance("DSA");
            signature.initSign(keyFactory);
            signature.update(bytes);
            return new String(Base64.getEncoder().encode(signature.sign()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 校验数字签名
     */
    public static boolean verify(String str, String sign,String publicKey) {
        try {
            byte[] bytes =Base64.getDecoder().decode(sign);
            DSAPublicKey keyFactory = (DSAPublicKey) KeyFactory.getInstance("DSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey.getBytes())));
            Signature signature=Signature.getInstance("DSA");
            signature.initVerify(keyFactory);
            signature.update(str.getBytes());
            return signature.verify(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
