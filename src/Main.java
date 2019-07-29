import java.util.Base64;

public class Main {

    private final static byte[] ENCRYPT_VAL = "HJGAHJVBAHJVAGVGGHJAVGHVAG".getBytes();

    public static void main(String[] args) {

        System.out.println("加密结果" + encrypt("A"));
//        System.out.println("解密结果" + encrypt(encrypt("A")));


        System.out.println(Integer.toBinaryString(~64));
        System.out.println(Integer.toBinaryString(~11));
//        int i=3^2;
        byte[] bytes = new byte[]{'A', 'B', 'C'};
        System.out.println((byte) ~bytes[0]);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ~bytes[i];
        }
        bytes = new String(bytes).getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ~bytes[i];
        }
        System.out.println(new String(bytes));
    }

    private static String encrypt(String str) {
        return new String(Base64.getEncoder().encode(str.getBytes()));

    }

    private static String decrypt(String str) {
        byte[] bytes = str.getBytes();
        int key = 65;
        for (int i = bytes.length - 1; i > 0; i--) {
            bytes[i] = (byte) (bytes[i] ^ bytes[i - 1]);
        }
        bytes[0] = (byte) (bytes[0] ^ key);
        return new String(bytes);
    }
}
