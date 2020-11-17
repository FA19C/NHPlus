package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Hilfsklasse für verschlüsselung
 */
public abstract class EncryptionHelper
{
    private static String schluesselInKlarText = "1234111234111234";
    private static byte[] schluesserinByte = schluesselInKlarText.getBytes(StandardCharsets.UTF_8);
    private static final String ALGORYTHMUS = "AES";
    private static SecretKeySpec schluesserVerschluesselt = new SecretKeySpec(schluesserinByte, ALGORYTHMUS);

    /**
     * Verschluesstelt den übergebenen String
     * @param zuVerschluesseln
     * @return
     */
    public static String encrypt(String zuVerschluesseln)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(ALGORYTHMUS);
            cipher.init(Cipher.ENCRYPT_MODE, schluesserVerschluesselt);
            return Base64.getEncoder().encodeToString(cipher.doFinal(zuVerschluesseln.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Entschluesselt den übergebenen String
     * @param zuentschluesseln
     * @return
     */
    public static String decrypt(String zuentschluesseln)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(ALGORYTHMUS);
            cipher.init(Cipher.DECRYPT_MODE, schluesserVerschluesselt);
            return new String(cipher.doFinal(Base64.getDecoder().decode(zuentschluesseln)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
