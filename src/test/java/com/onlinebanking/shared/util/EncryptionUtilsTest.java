package com.onlinebanking.shared.util;

import com.onlinebanking.TestUtils;
import com.onlinebanking.enums.ErrorMessage;
import com.onlinebanking.excepton.EncryptionException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

class EncryptionUtilsTest {

    public static final String ALGORITHM = "AES";
    private SecretKeySpec secretKeySpec;
    private SecretKey secretKey;

    @BeforeEach
    void setUp() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128, SecureRandom.getInstanceStrong());
        secretKey = keyGen.generateKey();
        secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
    }

    @AfterEach
    void tearDown() {
        EncryptionUtils.clearSecret(secretKey);
        // After encryption or decryption with key
        EncryptionUtils.clearSecret(secretKeySpec);
    }

    @Test
    void callingConstructorShouldThrowException() {
        TestUtils.assertExceptionCause(
                EncryptionUtils.class,
                AssertionError.class
        );
    }

    @Test
    void callingConstructorShouldThrowExceptionWithMessage() {
        TestUtils.assertExceptionCause(
                EncryptionUtils.class,
                AssertionError.class,
                ErrorMessage.NOT_INSTANTIABLE.getErrorMsg()
        );
    }

    @Test
    @DisplayName("testEncryptWithWrongAlgorithmShouldThrowException")
    void testEncryptWithWrongAlgorithmShouldThrowException(TestInfo testInfo) {

        String algo = testInfo.getDisplayName();
        byte[] text = algo.getBytes(StandardCharsets.UTF_8);

        Assertions.assertThrows(EncryptionException.class, () -> EncryptionUtils.encrypt(text, secretKeySpec, algo));
    }

    @Test
    @DisplayName("testDecryptWithWrongAlgorithmShouldThrowException")
    void testDecryptWithWrongAlgorithmShouldThrowException(TestInfo testInfo) {

        String algo = testInfo.getDisplayName();
        byte[] text = algo.getBytes(StandardCharsets.UTF_8);

        Assertions.assertThrows(EncryptionException.class, () -> EncryptionUtils.decrypt(text, secretKeySpec, algo));
    }

    @Test
    @DisplayName("testEncryptThenDecrypt")
    void testEncryptThenDecrypt(TestInfo testInfo) {

        String text = testInfo.getDisplayName();
        byte[] encrypt = EncryptionUtils.encrypt(text.getBytes(StandardCharsets.UTF_8), secretKeySpec);
        byte[] decrypt = EncryptionUtils.decrypt(encrypt, secretKeySpec);

        Assertions.assertEquals(testInfo.getDisplayName(), new String(decrypt, StandardCharsets.UTF_8));
    }

    @Test
    void testEncryptEmptyTextThrowsException() {
        byte[] bytes = "".getBytes(StandardCharsets.UTF_8);

        Assertions.assertThrows(IllegalArgumentException.class, () -> EncryptionUtils.encrypt(bytes, secretKeySpec));
    }

    @Test
    void testGenerateIvWithInvalidNumberThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EncryptionUtils.generateIv(0));
    }

    @Test
    void testWhenGenerateIvThrowsException() throws Exception {
        try (MockedStatic<SecureRandom> mocked = Mockito.mockStatic(SecureRandom.class)) {

            FieldUtils.writeStaticField(EncryptionUtils.class, "prng", null, true);
            mocked.when(SecureRandom::getInstanceStrong).thenThrow(new NoSuchAlgorithmException());
            Assertions.assertThrows(EncryptionException.class, () -> EncryptionUtils.generateIv(12));
        }

    }
}