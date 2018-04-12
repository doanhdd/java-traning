package com.huuanh.demo.rsa;

import com.huuanh.demo.rsa.common.RsaUtils;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestRSA {

  @Test
  public void testRSA() throws Exception {
    // generate public and private keys
    KeyPair keyPair = RsaUtils.buildKeyPair(RsaUtils.KEY_SIZE);
    PublicKey pubKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();


    System.out.println("Public Key: " + RsaUtils.getPublicKeyString(pubKey));
    System.out.println("Private Key: " + RsaUtils.getPrivateKeyString(privateKey));

    System.err.println("Public key format: " + pubKey.getFormat());
    System.err.println("Private key format: " + privateKey.getFormat());

    // encrypt the message
    byte[] encrypted = RsaUtils.encrypt(privateKey, "This is a secret message");

    // decrypt the message
    byte[] secret = RsaUtils.decrypt(pubKey, encrypted);
    System.out.println(new String(secret));

//    byte[] encrypt = RsaUtils.encrypt(RsaUtils.getPrivateKey(RsaUtils.getPrivateKeyString(privateKey)), "This is a secret message 2");
//
//    System.out.println(new String(RsaUtils.decrypt(RsaUtils.getPublicKey(RsaUtils.getPublicKeyString(pubKey)), encrypt)));

  }


}
