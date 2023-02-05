package provisio.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* Methods used to hash passwords from the registration and login forms */

public class HashClass 
{
    public static String hashValue(String HASH_THIS)
    {
        String valueToHash = HASH_THIS;
        return getSecureValue(valueToHash);
    }

    private static String getSecureValue(String valueToHash) {
        String generatedValue = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Get the hash's bytes
            byte[] bytes = md.digest(valueToHash.getBytes());
            
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            
            // Get complete hashed password in hex format
            generatedValue = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedValue;
    }
}
