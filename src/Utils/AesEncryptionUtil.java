package Utils;

import Exceptions.PasswordIsWrong;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

public class AesEncryptionUtil {

    // Genero la chiave AES a 32 byte dal byte array preso dalla stringa dell'utente
    private static SecretKeySpec generateKey(byte[] keyInput) {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Non dovrebbe essere stampato");
        }
        byte[] hash = sha.digest(keyInput);
        return new SecretKeySpec(hash, "AES");
    }

    // Genero un IV casuale di 16 byte
    private static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static String encrypt(String plaintext, String keyString) throws PasswordIsWrong {
        try {
            //genero la chiave e l'iv
            SecretKeySpec keySpec = generateKey(keyString.getBytes("UTF-8"));
            IvParameterSpec ivSpec = generateIv();

            //istanzio il cifrario
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            //cifro il plaintext
            byte[] encrypted = cipher.doFinal(plaintext.getBytes("UTF-8"));

            byte[] iv = ivSpec.getIV();

            //metto il ciphertext nel formato iv+ciphertext
            byte[] ivAndEncrypted = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, ivAndEncrypted, 0, iv.length);
            System.arraycopy(encrypted, 0, ivAndEncrypted, iv.length, encrypted.length);
            return Base64.getEncoder().encodeToString(ivAndEncrypted);
        }catch (Exception e) {
            throw new PasswordIsWrong("Something went wrong while encrypting");
        }
    }

    public static String decrypt(String ivAndCiphertextB64, String keyString) throws PasswordIsWrong {
        try {
            //metto il ciphertext in un bytearray e lo divido dal plaintext
            byte[] ivAndCiphertext = Base64.getDecoder().decode(ivAndCiphertextB64);
            byte[] iv = Arrays.copyOfRange(ivAndCiphertext, 0, 16);
            byte[] ciphertext = Arrays.copyOfRange(ivAndCiphertext, 16, ivAndCiphertext.length);

            SecretKeySpec keySpec = generateKey(keyString.getBytes("UTF-8"));
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            //istanzio il cifrario
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            //decifro e ritorno il plaintext
            byte[] decrypted = cipher.doFinal(ciphertext);
            return new String(decrypted, "UTF-8");
        }catch (Exception e) {
            throw new PasswordIsWrong("The password is incorrect!");
        }
    }

}