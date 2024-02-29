package services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Decryptor {

    public String decryptString(String hashedPassword) throws NoSuchAlgorithmException {
        // Iterate over all possible password combinations
        for (int i = 0; i < 1000000; i++) {
            String password = String.format("%06d", i); // Pad with leading zeros if needed
            String hashedAttempt = encryptString(password); // Hash the attempt
            if (hashedAttempt.equals(hashedPassword)) {
                return password; // Return the original password if the hash matches
            }
        }
        return null; // Return null if the password couldn't be decrypted
    }

    public String encryptString(String input) throws NoSuchAlgorithmException {

        //MessageDigest works with MD2, MD5, SHA-1, SHA-224, SHA-256
        //SHA-384 and SHA-512
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(input.getBytes());

        BigInteger bigInt = new BigInteger(1,messageDigest);

        return bigInt.toString(16);
    }
}
