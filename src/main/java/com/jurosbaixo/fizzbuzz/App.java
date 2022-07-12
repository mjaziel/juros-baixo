package com.jurosbaixo.fizzbuzz;

import com.jurosbaixo.fizzbuzz.service.DiscoverTreasureUseCase;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {

        DiscoverTreasureUseCase useCase = new DiscoverTreasureUseCase();

        String treasure = useCase.getTreasure();

        if (treasure != null )
            System.out.println("Treasure = "  +treasure);
        else
            System.out.println("Treasure not found. Try again!");
    }
}
