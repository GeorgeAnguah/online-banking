package com.onlinebanking.backend.service.security;

import com.onlinebanking.backend.service.security.impl.EncryptionServiceImpl;
import com.onlinebanking.exception.EncryptionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

class EncryptionServiceTest {

    protected EncryptionService encryptionService;

    @BeforeEach
    void setUp() {
        encryptionService = new EncryptionServiceImpl();

        ReflectionTestUtils.setField(encryptionService, "salt", "salt");
        ReflectionTestUtils.setField(encryptionService, "password", "password");
    }

    @Test
    void encryptAndDecryptRequestUri() {
        String uri = "http://localhost:8080/user/forgot/change?id=MKbscZZTo3jMpiUM"
                     + "Hh2n9fZUpqtTBcLrA+TOvhithkc=&token=ybrzAV891Y/28P7kf7mhz8GlRdlmNiXdjGc3eVJrkUyI"
                     + "6dnmuPTcwVxqWbWP0wsC";
        String encryptRequestUri = encryptionService.encrypt(uri);
        Assertions.assertNotNull(encryptRequestUri);
        Assertions.assertNotEquals(encryptRequestUri, uri);

        String decryptRequestUri = encryptionService.decrypt(encryptRequestUri);
        Assertions.assertNotNull(decryptRequestUri);
        Assertions.assertNotEquals(decryptRequestUri, encryptRequestUri);

        Assertions.assertEquals(uri, decryptRequestUri);
    }

    @Test
    void encryptWithNullThrowsException() {
        Assertions.assertNull(encryptionService.encrypt(null));
    }

    @Test
    void decryptWithNullThrowsException() {
        Assertions.assertNull(encryptionService.decrypt(null));
    }

    @Test
    void encryptThrowingExceptionShouldBeHandled(TestInfo testInfo) {
        try (MockedStatic<Cipher> cipher = Mockito.mockStatic(Cipher.class)) {
            cipher.when(() -> Cipher.getInstance(ArgumentMatchers.any())).thenThrow(new NoSuchAlgorithmException());

            String displayName = testInfo.getDisplayName();
            Assertions.assertThrows(EncryptionException.class, () -> encryptionService.encrypt(displayName));
        }
    }

    @Test
    void decryptThrowingExceptionShouldBeHandled(TestInfo testInfo) {
        try (MockedStatic<Cipher> cipher = Mockito.mockStatic(Cipher.class)) {
            cipher.when(() -> Cipher.getInstance(ArgumentMatchers.any())).thenThrow(new NoSuchAlgorithmException());

            String displayName = testInfo.getDisplayName();
            Assertions.assertThrows(EncryptionException.class, () -> encryptionService.decrypt(displayName));
        }
    }

    @Test
    void generatingSecretKeyInEncryptThrowingExceptionShouldBeHandled(TestInfo testInfo) {
        try (MockedStatic<SecretKeyFactory> mockStatic = Mockito.mockStatic(SecretKeyFactory.class)) {
            mockStatic.when(() -> {
                String any = ArgumentMatchers.any();
                SecretKeyFactory.getInstance(any);
            }).thenThrow(new NoSuchAlgorithmException());

            String displayName = testInfo.getDisplayName();
            Assertions.assertThrows(EncryptionException.class, () -> encryptionService.encrypt(displayName));
        }
    }

    @Test
    void generatingSecretKeyInDecryptThrowingExceptionShouldBeHandled(TestInfo testInfo) throws Exception {
        SecretKeyFactory mock = Mockito.mock(SecretKeyFactory.class);
        Mockito.when(mock.generateSecret(ArgumentMatchers.any())).thenThrow(new InvalidKeySpecException());

        String displayName = testInfo.getDisplayName();
        Assertions.assertThrows(EncryptionException.class, () -> encryptionService.decrypt(displayName));
    }

}