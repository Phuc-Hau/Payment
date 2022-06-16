package vn.com.smartpay.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashUtlis {

    public static String SHA256(String s) {
        String tym=null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encode = digest.digest(s.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encode.length);
            for (int i = 0; i < encode.length; i++) {
                String hex = Integer.toHexString(0xff & encode[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            tym = String.valueOf(hexString).toUpperCase();
        }catch (Exception e){
            e.printStackTrace();
        }

        return tym;
    }


}
