package io.github.joeljeremy.externalizedproperties.samples.decryption;

import io.github.joeljeremy.externalizedproperties.core.ExternalizedProperties;
import io.github.joeljeremy.externalizedproperties.core.processing.processors.DecryptProcessor;
import io.github.joeljeremy.externalizedproperties.core.processing.processors.DecryptProcessor.Decryptor;
import io.github.joeljeremy.externalizedproperties.core.processing.processors.DecryptProcessor.JceDecryptor;
import io.github.joeljeremy.externalizedproperties.core.resolvers.MapResolver;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class Application {
  // Encryption/decryption parameters
  private static final String RSA_ALGORITHM = "RSA";
  private static final String AES_ALGORITHM = "AES";
  private static final String AES_GCM_NO_PADDING_TRANSFORMATION = "AES/GCM/NoPadding";
  private static final KeyPair RSA_KEY_PAIR = generateRsaKeyPair();
  private static final SecretKey AES_SECRET_KEY = generateAesSecretKey();
  private static final GCMParameterSpec GCM_PARAMETER_SPEC = createGcmParameterSpec();

  public static void main(String[] args)
      throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
          InvalidAlgorithmParameterException {
    ExternalizedProperties externalizedProperties = buildExternalizedProperties();

    ApplicationProperties applicationProperties =
        externalizedProperties.initialize(ApplicationProperties.class);

    String aesDecryptedProperty = applicationProperties.aesDecryptedProperty();
    System.out.println("aes.encrypted.property decrypted value: " + aesDecryptedProperty);

    String rsaDecryptedProperty = applicationProperties.rsaDecryptedProperty();
    System.out.println("rsa.encrypted.property decrypted value: " + rsaDecryptedProperty);
  }

  private static ExternalizedProperties buildExternalizedProperties()
      throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
          InvalidAlgorithmParameterException {
    // For MapResolver.
    Map<String, String> map = new HashMap<>();
    map.put(
        "aes.encrypted.property",
        // Encrypted using AES/GCM/NoPadding algorithm/transformation.
        // Encrypted value is Base64-encoded so that it can be
        // represented as a String.
        aesEncryptBase64("plaintext"));

    map.put(
        "rsa.encrypted.property",
        // Encrypted using RSA algorithm.
        // Encrypted value is Base64-encoded so that it can be
        // represented as a String.
        rsaEncryptBase64("plaintext"));

    // Print the encrypted map entries.
    map.entrySet().forEach(System.out::println);

    return ExternalizedProperties.builder()
        .defaults()
        .resolvers(new MapResolver(map))
        .processors(new DecryptProcessor(rsaDecryptor(), aesDecryptor()))
        .build();
  }

  private static Decryptor rsaDecryptor()
      throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
    return JceDecryptor.factory().asymmetric(RSA_ALGORITHM, RSA_KEY_PAIR.getPrivate());
  }

  private static Decryptor aesDecryptor()
      throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
          InvalidAlgorithmParameterException {
    return JceDecryptor.factory()
        .symmetric(AES_GCM_NO_PADDING_TRANSFORMATION, AES_SECRET_KEY, GCM_PARAMETER_SPEC);
  }

  private static String rsaEncryptBase64(String value) {
    try {
      Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, RSA_KEY_PAIR.getPublic());
      return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
    } catch (Exception e) {
      throw new IllegalArgumentException("Exception occurred while encrypting.", e);
    }
  }

  private static String aesEncryptBase64(String value) {
    try {
      Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING_TRANSFORMATION);
      cipher.init(Cipher.ENCRYPT_MODE, AES_SECRET_KEY, GCM_PARAMETER_SPEC);
      return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
    } catch (Exception e) {
      throw new IllegalArgumentException("Exception occurred while encrypting.", e);
    }
  }

  private static KeyPair generateRsaKeyPair() {
    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
      keyPairGenerator.initialize(1024);
      return keyPairGenerator.generateKeyPair();
    } catch (Exception ex) {
      throw new IllegalStateException("Failed to create the RSA key pair.");
    }
  }

  private static SecretKey generateAesSecretKey() {
    try {
      KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
      keyGenerator.init(256);
      return keyGenerator.generateKey();
    } catch (Exception ex) {
      throw new IllegalStateException("Failed to create the AES secret key.");
    }
  }

  private static GCMParameterSpec createGcmParameterSpec() {
    byte[] iv = new byte[12];
    SecureRandom secureRandom = new SecureRandom();
    secureRandom.nextBytes(iv);
    return new GCMParameterSpec(128, iv);
  }
}
