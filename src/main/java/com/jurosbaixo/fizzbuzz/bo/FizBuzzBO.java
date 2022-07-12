package com.jurosbaixo.fizzbuzz.bo;

import java.util.List;
import java.util.stream.Collectors;

public class FizBuzzBO {

    /**
     *  Translates a number into a word according to fizzbuzz rule
     * @return  fizzbuzz word
     */
    public static String translate(Integer num) {

        if (num % 15 == 0)
            return "fizzbuzz";
        if (num % 3 == 0)
            return "fizz";
        if (num % 5 == 0)
            return "buzz";

        return num.toString();
    }

    /**
     *  Translates a List of number into a List of fizzbuzz words
     * @return list of fizzbuzz words
     */
    public static List<String> translate(List<Integer> intList) {
        return  intList.stream()
                .map(num -> translate(num))
                .collect(Collectors.toList());
    }

}
