package com.huuanh.demo.rsa;

import com.huuanh.demo.rsa.common.RsaAlgorithm;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyPair;

@SpringBootTest
public class TestRsaAlgorithm {

    @Test
    public void test() throws Exception {
        //First generate a public/private key pair
        KeyPair pair = RsaAlgorithm.buildKeyPair(RsaAlgorithm.KEY_SIZE);

        //Our secret message
        String message = "the answer to life the universe and everything";

        //Encrypt the message
        String cipherText = RsaAlgorithm.encrypt(message, pair.getPublic());

        //Now decrypt it
        String decipheredMessage = RsaAlgorithm.decrypt(cipherText, pair.getPrivate());

        System.out.println(decipheredMessage);
        String sign = RsaAlgorithm.signature(message, pair.getPrivate());

        System.out.println(sign);
        System.out.println(RsaAlgorithm.verify(message, sign, pair.getPublic()));
    }

}
