package org.kuali.rice.core.api.encryption;

public interface EncryptionService {
    String encrypt(Object valueToHide) throws java.security.GeneralSecurityException;
    byte[] encryptBytes(byte[] valueToHide) throws java.security.GeneralSecurityException;
    String decrypt(String ciphertext) throws java.security.GeneralSecurityException;
    byte[] decryptBytes(byte[] ciphertext) throws java.security.GeneralSecurityException;
    boolean isEnabled();
    String hash(Object valueToHide) throws java.security.GeneralSecurityException;
}
