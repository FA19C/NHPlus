package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
     * @param zuVerschluesseln der zuverschluesselnde String
     * @return der String verschlusselt
     */
    public static String encryptString(String zuVerschluesseln)
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
     * @param zuentschluesseln der zu entschlüsselnde String
     * @return der entschluesselte String
     */
    public static String decryptString(String zuentschluesseln)
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

    /**
     * Entschluesselt die übergebene File nach ziel
     * @param zuEntschluesseln was zu entschlüsseln ist
     * @param ziel wo sie hin entschlüsselt werden soll
     */
    public static void decryptFile(File zuEntschluesseln, File ziel)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(ALGORYTHMUS);
            cipher.init(Cipher.DECRYPT_MODE, schluesserVerschluesselt);

            FileInputStream inputStream = new FileInputStream(zuEntschluesseln);
            byte[] inputBytes = new byte[(int) zuEntschluesseln.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(ziel);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * verschluesselt die übergebene File nach ziel
     * @param zuVerschluesseln was zu verschlüsseln ist
     * @param ziel wo sie hin verschlüsselt werden soll
     */
    public static void encryptFile(File zuVerschluesseln, File ziel)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(ALGORYTHMUS);
            cipher.init(Cipher.ENCRYPT_MODE, schluesserVerschluesselt);

            FileInputStream inputStream = new FileInputStream(zuVerschluesseln);
            byte[] inputBytes = new byte[(int) zuVerschluesseln.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(ziel);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
