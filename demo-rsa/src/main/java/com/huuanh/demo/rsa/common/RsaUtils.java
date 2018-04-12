package com.huuanh.demo.rsa.common;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class RsaUtils {

  public static final int KEY_SIZE = 2048;

  public static String[] genPrivateAndPublicKeys() throws NoSuchAlgorithmException {
    KeyPair keyPair = RsaUtils.buildKeyPair(RsaUtils.KEY_SIZE);
    PrivateKey privateKey = keyPair.getPrivate();
    PublicKey pubKey = keyPair.getPublic();
    return new String[]{RsaUtils.getPrivateKeyString(privateKey),
        RsaUtils.getPublicKeyString(pubKey)};
  }

  public static KeyPair buildKeyPair(int keySize) throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(keySize);
    return keyPairGenerator.genKeyPair();
  }

  public static String getPublicKeyString(PublicKey publicKey) {
    return Base64.getEncoder().encodeToString(publicKey.getEncoded());
  }

  public static String getPrivateKeyString(PrivateKey privateKey) {
    return Base64.getEncoder().encodeToString(privateKey.getEncoded());
  }

  public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);

    return cipher.doFinal(message.getBytes());
  }

  public static byte[] decrypt(PublicKey publicKey, byte[] encrypted) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, publicKey);

    return cipher.doFinal(encrypted);
  }

  public static PrivateKey getPrivateKey(String privateKey) throws Exception {
    PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePrivate(ks);
  }

  public static PublicKey getPublicKey(String publicKey) throws Exception {
    X509EncodedKeySpec ks = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePublic(ks);
  }

}
