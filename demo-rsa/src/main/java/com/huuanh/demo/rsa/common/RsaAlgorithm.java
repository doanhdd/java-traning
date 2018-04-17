package com.huuanh.demo.rsa.common;

import com.huuanh.demo.rsa.exception.ApiException;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static com.huuanh.demo.rsa.common.Constants.SIGNATURE_TEXT;
import static java.nio.charset.StandardCharsets.UTF_8;

public class RsaAlgorithm {
    public static final int KEY_SIZE = 2048;

    public static String[] genPrivateAndPublicKeys() throws Exception {
        KeyPair keyPair = buildKeyPair(KEY_SIZE);
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey pubKey = keyPair.getPublic();
        return new String[]{getPrivateKeyString(privateKey),
                getPublicKeyString(pubKey)};
    }

    public static KeyPair buildKeyPair(int keySize) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
        keyPairGenerator.initialize(keySize, new SecureRandom());
        return keyPairGenerator.genKeyPair();
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding", "BC");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);
        Cipher decryptCipher = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding", "BC");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(decryptCipher.doFinal(bytes), UTF_8);
    }

    public static String signature(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }

    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        KeyFactory kf = KeyFactory.getInstance("RSA", "BC");
        return kf.generatePrivate(ks);
    }

    public static PublicKey getPublicKey(String publicKey) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        Provider[] p = Security.getProviders();
        X509EncodedKeySpec ks = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
        KeyFactory kf = KeyFactory.getInstance("RSA", "BC");
        return kf.generatePublic(ks);
    }

    private static String getPublicKeyString(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    private static String getPrivateKeyString(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public static String signatureOrder(String plainText) {
        if (StringUtils.isEmpty(plainText)) {
            throw new ApiException("Can't signature");
        }
        return Constants.SIGNATURE_TEXT + plainText;
    }

    public static String verifyOrder(String plainText) {
        if(!StringUtils.isEmpty(plainText) && plainText.indexOf(SIGNATURE_TEXT, 0) == 0) {
            return plainText.substring(plainText.indexOf(SIGNATURE_TEXT, 0) + SIGNATURE_TEXT.length());
        }
        throw new ApiException("Can't verify");
    }
}
