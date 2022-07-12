package com.jurosbaixo.fizzbuzz.service;

import com.jurosbaixo.fizzbuzz.bo.Sha256Encoder;
import com.jurosbaixo.fizzbuzz.bo.FizBuzzBO;
import com.jurosbaixo.fizzbuzz.client.FizzBuzzClient;
import com.jurosbaixo.fizzbuzz.client.FizzBuzzClientBuilder;
import com.jurosbaixo.fizzbuzz.converter.Converter;

import java.util.List;

public class DiscoverTreasureUseCase {

    /**
     *  Tries 10 times to find the treasure using delete if not finding resets iterates four times more.
     *
     * @return null if treasure was not found or the value held as treasure.
     */
    public String getTreasure() throws Exception {

        FizzBuzzClient client = new FizzBuzzClientBuilder().build();

        String treasure = null;

        int attemptsOnReset = 0;

        while (treasure == null && attemptsOnReset < 5) {

            int attemptsOnDelete = 0;

            while (treasure == null && attemptsOnDelete < 10) {

                try {

                    // get list of numbers
                    List<Integer> intList = client.getFizzBuzz();

                    // translates list of numbers into list of fizzbuzz words
                    List<String> strList = FizBuzzBO.translate(intList);

                    // convert list<String> to json
                    String listJson = Converter.toJson(strList);

                    //System.out.println(listJson);

                    // encode json into sha256
                    String shaHash = Sha256Encoder.encode(listJson);

                    //System.out.println(shaHash);

                    // post json + shaHash
                    if (client.postFizzBuzz(shaHash, listJson)) {
                        try {
                            // try to retrieve the treasure
                            treasure = client.getFizzBuzzTreasure(shaHash);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());

                            // flush current range and unlock new range to keep on trying
                            client.deleteFizzBuzz(shaHash);

                            // keep track of number of attempts per delete
                            attemptsOnDelete++;

                           // System.out.println("attemptsOnDelete : " + attemptsOnDelete);
                        }
                    }

                } catch (Exception e) {
                   // System.out.println(e.getMessage());
                    // keep track of number of attempts on delete
                    attemptsOnDelete++;
                   // System.out.println("attemptsOnDelete : " + attemptsOnDelete);
                }
            }

            // reset to start all over again
            client.resetFizzBuzz();
            attemptsOnReset++;
           // System.out.println("attemptsOnReset : " + attemptsOnReset);
        }

        return treasure;
    }

}
