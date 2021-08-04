package com.onlinebanking.backend.service.security;

import com.onlinebanking.constant.ProfileTypeConstants;
import com.onlinebanking.excepton.EncryptionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@SpringBootTest
@ActiveProfiles(value = {ProfileTypeConstants.TEST})
class EncryptionServiceTest {

    @Autowired
    protected EncryptionService encryptionService;

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
        Assertions.assertThrows(IllegalArgumentException.class, () -> encryptionService.encrypt(null));
    }

    @Test
    void decryptWithNullThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> encryptionService.decrypt(null));
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