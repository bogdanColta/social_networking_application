package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Base64;

public class SecurityCheck {

    public static String PROVIDER = "SUN";
    public static String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
    public static String HASH_FUNCTION = "SHA-256";

    private static final char[] CHARS_ARRAY = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
    private static final Base64.Encoder encoder = Base64.getEncoder();

    public static String[] hashSaltFromPassword(String passwordString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_FUNCTION);
            SecureRandom srd = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM, PROVIDER);
            String salt = getRandomString(20);
            String saltPlusPlainTextPassword = passwordString + salt;
            messageDigest.update(saltPlusPlainTextPassword.getBytes());
            String[] toReturn = new String[2];
            toReturn[0] = encoder.encodeToString(messageDigest.digest());
            toReturn[1] = salt;
            return toReturn;
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean passwordsEqual(String plainPassword, String salt, String hashedPasswordStored) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_FUNCTION);
            String saltPlusPlainTextPassword = plainPassword + salt;
            messageDigest.update(saltPlusPlainTextPassword.getBytes());
            String hashedPasswordToCheck = encoder.encodeToString(messageDigest.digest());
            return hashedPasswordToCheck.equals(hashedPasswordStored);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getRandomString() {
        return getRandomString(50);
    }

    public static String getRandomString(int charsNumber) {
        try {
            StringBuilder sb = new StringBuilder();
            SecureRandom srd = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM, PROVIDER);
            for (int i = 0; i < charsNumber; i++) {
                sb.append(CHARS_ARRAY[srd.nextInt(CHARS_ARRAY.length)]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        }
    }
}
