package com.jurosbaixo.fizzbuzz.client;

public class FizzBuzzClientBuilder {

    private static String URL_BASE = "https://codetest.jurosbaixos.com.br/v1/fizzbuzz";
    public static String X_API_KEY = "MarceloThisWillBeAPieceOfCake!";


    public FizzBuzzClient build() {
        FizzBuzzClient client = new FizzBuzzClient(URL_BASE, X_API_KEY );
        return client;
    }


}
