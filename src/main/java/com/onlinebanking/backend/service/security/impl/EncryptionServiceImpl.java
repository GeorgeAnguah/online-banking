package com.onlinebanking.backend.service.security.impl;

import com.onlinebanking.backend.service.security.EncryptionService;
import com.onlinebanking.excepton.EncryptionException;
import com.onlinebanking.shared.util.validation.InputValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

/**
 * This is the implementation of the encryption service.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class EncryptionServiceImpl implements EncryptionService {

    private static final String ERROR_ENCRYPTING_DATA = "Error encrypting data";
    private static final String ERROR_DECRYPTING_DATA = "Error decrypting data";
    private static final String DERIVATION_FUNCTION = "PBKDF2WithHmacSHA256";
    private static final String ENCRYPT_ALGORITHM = "AES/GCM/NoPadding";
    private static final String AES_ALGORITHM = "AES";

    private static final int GCM_TAG_LENGTH = 12; // bits
    private static final int GCM_IV_LENGTH = 12;
    // The password-based key derivation function
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 256;

    @Value("${encryption.secret.password}")
    private String password;

    @Value("${encryption.secret.salt}")
    private String salt;

    /**
     * Encrypts the text to be sent out.
     *
     * @param text the text
     *
     * @return the encrypted text
     */
    @Override
    public String encrypt(String text) {
        InputValidationUtils.validateInputs(getClass(), text);

        try {
            byte[] iv = new byte[GCM_IV_LENGTH];
            (new SecureRandom()).nextBytes(iv);

            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
            GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
            cipher.init(Cipher.ENCRYPT_MODE, getKeyFromPassword(), ivSpec);

            byte[] ciphertext = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            byte[] encrypted = new byte[iv.length + ciphertext.length];
            System.arraycopy(iv, 0, encrypted, 0, iv.length);
            System.arraycopy(ciphertext, 0, encrypted, iv.length, ciphertext.length);

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (NoSuchAlgorithmException
                | IllegalArgumentException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException
                | NoSuchPaddingException e) {
            LOG.debug(ERROR_ENCRYPTING_DATA, e);
            throw new EncryptionException(e);
        }
    }

    /**
     * Decrypts the encryptedText to be sent out.
     *
     * @param encryptedText the encryptedText
     *
     * @return the decrypted uri
     */
    @Override
    public String decrypt(String encryptedText) {
        InputValidationUtils.validateInputs(getClass(), encryptedText);

        try {
            byte[] decoded = Base64.getDecoder().decode(encryptedText);

            byte[] iv = Arrays.copyOfRange(decoded, 0, GCM_IV_LENGTH);

            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
            GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
            cipher.init(Cipher.DECRYPT_MODE, getKeyFromPassword(), ivSpec);

            byte[] ciphertext = cipher.doFinal(decoded, GCM_IV_LENGTH, decoded.length - GCM_IV_LENGTH);

            return new String(ciphertext, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException
                | IllegalArgumentException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException
                | NoSuchPaddingException e) {
            LOG.debug(ERROR_DECRYPTING_DATA, e);
            throw new EncryptionException(e);
        }
    }

    private SecretKey getKeyFromPassword() {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(DERIVATION_FUNCTION);
            byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, ITERATION_COUNT, KEY_LENGTH);

            return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), AES_ALGORITHM);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new EncryptionException(e);
        }
    }
}
