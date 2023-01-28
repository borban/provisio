package provisio.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class HashClass 
{
    public static String hashValue(String HASH_THIS)
            throws NoSuchAlgorithmException, NoSuchProviderException 
    {
        String valueToHash = HASH_THIS;
        String salt = getSalt();
        
        String secureValue = getSecureValue(valueToHash, salt);

        return secureValue;
    }

    private static String getSecureValue(String valueToHash,
            String salt) {
        String generatedValue = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Add password bytes to digest
            md.update(salt.getBytes());
            
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

    // Add salt
    private static String getSalt()
            throws NoSuchAlgorithmException, NoSuchProviderException 
    {
        // Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");

        // Create array for salt
        byte[] salt = new byte[16];

        // Get a random salt
        sr.nextBytes(salt);

        // return salt
        return salt.toString();
    }
}