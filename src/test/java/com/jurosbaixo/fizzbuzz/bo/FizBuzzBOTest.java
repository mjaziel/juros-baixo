package com.jurosbaixo.fizzbuzz.bo;

import org.junit.Assert;
import org.junit.Test;

public class FizBuzzBOTest {


    @Test
    public void givenDivisibleByThree_whenTranslate_thenReturnFizz() {

        int given = 9;
        String expected = "fizz";

        String actual = FizBuzzBO.translate(given);

        Assert.assertEquals(expected, actual);

    }

}