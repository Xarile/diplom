package main.basekriteriev;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordHash {
    public static void main(String[] args) {
        System.out.println(doHashing(""));

    }
    public static String doHashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuffer sb = new StringBuffer();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
