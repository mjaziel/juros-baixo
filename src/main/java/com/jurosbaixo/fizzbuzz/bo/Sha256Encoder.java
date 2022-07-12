package com.jurosbaixo.fizzbuzz.bo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Encoder {

    /**
     *  Encode the input into sha-256 hexadecimal format
     * @param date
     * @return shaHash in hexadecimal
     * @throws NoSuchAlgorithmException
     */
    public static String encode(String data )  throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());

    }

    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }


}
