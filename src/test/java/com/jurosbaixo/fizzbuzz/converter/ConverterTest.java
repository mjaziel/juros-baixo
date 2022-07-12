package com.jurosbaixo.fizzbuzz.converter;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ConverterTest {

    @Test
    public void givenListString_whenConvert_thenJsonOK() {

       List<String> given = Arrays.asList("fizz", "buzz", "2", "fizzbuzz");
       String expected = "[\"fizz\",\"buzz\",\"2\",\"fizzbuzz\"]";

       String actual =  Converter.toJson(given);

        Assert.assertEquals(expected, actual);
    }
}