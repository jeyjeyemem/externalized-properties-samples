package io.github.joeljeremy.externalizedproperties.samples.decryption;

import io.github.joeljeremy.externalizedproperties.core.ExternalizedProperty;
import io.github.joeljeremy.externalizedproperties.core.processing.Decrypt;

public interface ApplicationProperties {
  @ExternalizedProperty("aes.encrypted.property")
  @Decrypt("AES/GCM/NoPadding")
  public String aesDecryptedProperty();

  @ExternalizedProperty("rsa.encrypted.property")
  @Decrypt("RSA")
  public String rsaDecryptedProperty();
}
