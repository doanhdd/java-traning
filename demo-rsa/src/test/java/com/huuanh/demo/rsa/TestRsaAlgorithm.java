package com.huuanh.demo.rsa;

import com.google.gson.Gson;
import com.huuanh.demo.rsa.common.Constants;
import com.huuanh.demo.rsa.common.RsaAlgorithm;
import com.huuanh.demo.rsa.viewmodel.OrderCreateListModel;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Cipher;
import java.security.*;

import static com.huuanh.demo.rsa.common.Constants.SIGNATURE_TEXT;

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

        String[] genkey = RsaAlgorithm.genPrivateAndPublicKeys();
        System.out.println("Private Key: " + genkey[0]);
        System.out.println("Public Key: " + genkey[1]);
    }

    @Test
    public void test2() throws Exception {
        //First generate a public/private key pair
        PrivateKey privateKey = RsaAlgorithm.getPrivateKey(Constants.PRIVATE_KEY_SERVER);
        PublicKey publicKey = RsaAlgorithm.getPublicKey(Constants.PUBLIC_KEY_SERVER);

        //Our secret message
        String message = "This is a text";

        //Encrypt the message
        String cipherText = RsaAlgorithm.encrypt(message, publicKey);

        //Now decrypt it
        String decipheredMessage = RsaAlgorithm.decrypt(cipherText, privateKey);

        System.out.println(decipheredMessage);

    }

    @Test
    public void test3() throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        byte[] input = "abc".getBytes();
        Cipher cipher = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding", "BC");
        SecureRandom random = new SecureRandom();
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");

        generator.initialize(386, random);

        KeyPair pair = generator.generateKeyPair();
        Key pubKey = pair.getPublic();

        Key privKey = pair.getPrivate();

        cipher.init(Cipher.ENCRYPT_MODE, pubKey, random);
        byte[] cipherText = cipher.doFinal(input);
        System.out.println("cipher: " + new String(cipherText));

        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("plain : " + new String(plainText));
    }

    @Test
    public void test4() throws Exception {
        PrivateKey privateKey = RsaAlgorithm.getPrivateKey(Constants.PRIVATE_KEY_SERVER);
        PublicKey publicKey = RsaAlgorithm.getPublicKey(Constants.PUBLIC_KEY_SERVER);

        String cipherText = RsaAlgorithm.encrypt("{\"products\":[{\"id\":1,\"quantity\":5}]}", publicKey);

        String data = RsaAlgorithm.decrypt(cipherText, privateKey);

        //Start Test
        //  String data = "{\"products\":[{\"id\":1,\"quantity\":5}]}"
        // End test
        Gson gson = new Gson();
        OrderCreateListModel orderCreateListModel = gson.fromJson(data, OrderCreateListModel.class);
        System.out.println(data);
    }

    @Test
    public void test5() throws Exception {
        PrivateKey privateKey = RsaAlgorithm.getPrivateKey(Constants.PRIVATE_KEY_SERVER);
        PublicKey publicKey = RsaAlgorithm.getPublicKey(Constants.PUBLIC_KEY_SERVER);

        String plainText = "orderId=BTsHGKQ70SLZF5BUdp7oUcMncnQX+faku/1lCUMSJGKUTeYGL0Ph6JgVxIwkwn6y6zIkk08QUw8cgwP8DmeWC8xm86uWgc1/VsaeVVT4dG49QXxR/y9vSrlmVpn8ZHJiCDl0gPNiyAkHCRmWO25NHdHKxiIQfFBiteuo6msEye+iSEIE8Z4adPuY1Eyjz/xsBUD/Ar1zMtVLSJ5qQwPOMHu2SpOda2h1fPw1sE8HZ+hosTRW2jqd5kyG/3KAmAXzGie52wH8bT4nfV7t3Fi13CJK1gLrveN6JezFhjFxGEUuXHF1H6+Ts3M4cv0OT1zFqxKAhsWu6yLhSG5HlF7v6w==";

        System.out.println(plainText.indexOf(SIGNATURE_TEXT, 0));

        String cipherText = plainText.substring(plainText.indexOf(SIGNATURE_TEXT, 0) + SIGNATURE_TEXT.length());
        System.out.println(cipherText);

//        String cipherText = RsaAlgorithm.encrypt(text, publicKey);
//        System.out.println(cipherText);
        String data = RsaAlgorithm.decrypt(cipherText, privateKey);
        System.out.println(data);
    }

    @Test
    public void test6() throws Exception {
        PublicKey publicKey = RsaAlgorithm.getPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7h8+mzbtYZE6BEZeZBzDf7z/wtZzJRWMgOTVFoaAd3YIa3HV4qF9zjnK6yZR7vGCfIVNZm247r8QpquJQuFNJ/fwcm0l4DM7lwEsfJS2WAcvr5EhJl245LE1Sy8twAE8S4ttiXzMLTDvSfUiE99gbDo/d9IlVaxIJgQI2JL9AS/6yqGJAvPaXY9cPJTx2ZKr0DKFPP2/WBbffUmvboim7kron9c8wvXn0gLixzxOWpjzOrkasVVMXOZL5Y0chdPohKBkkuv20OgOnKNLDWrKU1ccEvqeiwFWHTZwBnBuPa+VoyNBQ7GcOVgmjqTiJDZPEccVR91vZNqN3CH0kM5whQIDAQAB");
        String cipherText = RsaAlgorithm.encrypt("8", publicKey);
//        String cipherText = RsaAlgorithm.encrypt("{\"products\":[{\"id\":1,\"quantity\":5}]}", publicKey);
        System.out.println(cipherText);
    }

    @Test
    public void test7() throws Exception {
        PrivateKey privateKey = RsaAlgorithm.getPrivateKey(Constants.PRIVATE_KEY_SERVER);
//        PrivateKey privateKey = RsaAlgorithm.getPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDuHz6bNu1hkToERl5kHMN/vP/C1nMlFYyA5NUWhoB3dghrcdXioX3OOcrrJlHu8YJ8hU1mbbjuvxCmq4lC4U0n9/BybSXgMzuXASx8lLZYBy+vkSEmXbjksTVLLy3AATxLi22JfMwtMO9J9SIT32BsOj930iVVrEgmBAjYkv0BL/rKoYkC89pdj1w8lPHZkqvQMoU8/b9YFt99Sa9uiKbuSuif1zzC9efSAuLHPE5amPM6uRqxVUxc5kvljRyF0+iEoGSS6/bQ6A6co0sNaspTVxwS+p6LAVYdNnAGcG49r5WjI0FDsZw5WCaOpOIkNk8RxxVH3W9k2o3cIfSQznCFAgMBAAECggEAZ7y24pnISrQhCVG6t3SMeu7UoYYZfbNolZKbfoe0GdcTDfP20Re3ngsxasYz9ZvDVAoi0RfF9ETKtUGPt9I9cccjOkZwtL69HCkHv3JuCeV/kcXxIiy8au0Eoa95B+fB48tnOfljXeCurpKbePrcn+m0Urf7SdqXR/CEBg3rKQNt5vat7bWMrZvke8j9rIBCEzwoqAIkzH6IGN6hXseqzsbsf1jziiM/x9+GpSGdWCbMPUcKQoXJ5XEr+5zhu+MTAGBK9c0Pt0UdHKywHVCFeWUA1c32UYmyH3HW42QniTyuihw4CmR03Cu/GCw3M8rvCXDEmjd8NwAvdZPsCi7vFQKBgQD4IbfG2pnDMS8NimpMcitFtKmIiLyJvbbRcjW/F9jtp6yWwVR34sEm1zyJIi2WdHzcQ/rVnJkN37yrZCXHoWtEoYnyXlRfwMEfohAsUjEoW/PPvxZxUAuqb/iM1+eUtOh3DDlRoGpeIV8bE6v3EQOqOLjw29rzY14VOOE1jkfzzwKBgQD1rEUy6CFUZQMMBAH6tLjMu5DFXKeZO2izv3dyJpA4LrUZ2oRJDs5wV/N0nmk64d4J4+AasRULLljk4qCte4Di6G3Uaef/CN0p1hMAOkO2e3V2SIPHFuWIu72jS0WDPPAdrUKhZn/fRKo6S3KUg2AYoLZJ+3r/gLmXVPRtbrknawKBgQCDT4Sr7V9V1p9p8QgT8I9J0jOtl37+/bDT4jbjK8GW8xb4qNMEprFE1r6oa7foWTkCb888YCT3HKIBqXv8y0WzCjGwW48XJTCOhvs8GTr+errqA9vil1O1HJBSKKfbIGmtJoDm25euvMZR0/U7SHvCB0aH4DFcgAd3/6Pe7a7SLQKBgDPQN2/7ITxpaXEZT0mO4ALd9DSLzBU3QGfYk9qRY41V3IAtZAT8iHGNOx13Y341sNypINTAUE3hYoIzgevUxmQU3UUVaQaS2gI+0hcUMr/D3gwqUxCkNZjW77IG6Aw7x/CaYBkmQwXVt4k/7FSCOLw4+Pk1fsVwYrQWCM8jsQ0tAoGAQMYMQFeGGQGMpGYgV08I65CM1lqqQjFfWuBCLZLM7NIOfOTGyRn3X7liP7F99ddzV2+XlGy6boktuEfH5f1qllbz8pyHx6CSWtVlSsz4cJRYaM8qAF7W9xzPDg4gz6izNFQlc0FJjt51jb6UG/p7BCA3W5r135rQZWjSjvCew7Q=");
        String cipherText = "dpt20fEOA/KlXtNxgIaFacoTIbdFCPByUFVnHdKlQOkZFXBpcTr4slec1KOsXk3/zhn08m+VZSltOlG9b29EOPYFQkQE+fpmuRq51pIgAV0jU8bDxM+Z9j7pwxtmcWP0SNyW/YV8I36S+Az2dzVG+/9AJ7rKK+HnD4oC0hxNh2QxqOYkWz6siWHzMC1Cgm7GA2J7nLfRyBERqA4nCLZ/uT5Vm+81jIf2gjkSUxGiuqquz+VLfePTGQHMU2VtNvSLRwKvvbjRkf02/qVel3GAO5iTNNFNPxBXBY5pQkcdp/f3/H9klbaNGRot1KS+5kcq/JAWh4p9R24zdEWZxdhe2Q==";
        String data = RsaAlgorithm.decrypt(cipherText, privateKey);
        System.out.println(data);
    }

}
