package com.jurosbaixo.fizzbuzz.bo;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class Encoder256Test {

    @Test
    public void givenJsonArray_whenEncoded_thenShaHashCorrect() throws NoSuchAlgorithmException {

        String given = "[\"fizz\",\"buzz\",\"fizzbuzz\"]";
        String expected = "c66a63862cf416c2acfe81ae697c066cff80b430af31fc9cae70957f355ded7d";

        String actual = Sha256Encoder.encode(given);

        Assert.assertEquals(expected, actual);

    }
}