package pl.swiderski.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    private String input;

    public Hash(String input) {
        this.input = input;
    }

    public String hash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("Sha-512");
            byte[] result = digest.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result){
                sb.append(Integer.toHexString(0xFF & b));
            }
            return sb.toString();
           // return String.format("%064x", new BigInteger(1, result));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not hash password");
        }
    }
}
