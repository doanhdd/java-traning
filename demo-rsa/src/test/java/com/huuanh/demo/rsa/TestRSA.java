package com.huuanh.demo.rsa;

import com.google.gson.Gson;
import com.huuanh.demo.rsa.common.RsaUtils;
import com.huuanh.demo.rsa.model.OrderDetail;
import com.huuanh.demo.rsa.viewmodel.OrderCreateListModel;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.List;
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
//    byte[] encrypted = RsaUtils.encrypt(privateKey, "This is a secret message");
//
//    // decrypt the message
//    byte[] secret = RsaUtils.decrypt(pubKey, encrypted);
//    System.out.println(new String(secret));

//    byte[] encrypted2 = RsaUtils.encrypt(pubKey, "[{\"id\":1,\"quantity\":5}]");
//    String data = Base64.getEncoder().encodeToString(encrypted2);
//    System.out.println(data);
////    // decrypt the message
////    byte[] secret2 = RsaUtils.decrypt(privateKey, encrypted2);
////    System.out.println(new String(secret2));
////    System.out.println(new String(RsaUtils.decrypt(privateKey, Base64.getDecoder().decode(data))));
//
//    byte[] b =  RsaUtils.decode(data);
//    String bs = new String(RsaUtils.decrypt(privateKey, b));
//    System.out.println(bs);
//    byte[] encrypt = RsaUtils.encrypt(RsaUtils.getPrivateKey(RsaUtils.getPrivateKeyString(privateKey)), "This is a secret message 2");
//
//    System.out.println(new String(RsaUtils.decrypt(RsaUtils.getPublicKey(RsaUtils.getPublicKeyString(pubKey)), encrypt)));


    Gson gson = new Gson();
    //Start Test
    String dataa = "{\"products\":[{\"id\":1,\"quantity\":5},{\"id\":1,\"quantity\":5}]}";
    // End test
    OrderCreateListModel orderCreateListModel = gson.fromJson(dataa, OrderCreateListModel.class);
    System.out.println(dataa);

  }


}
