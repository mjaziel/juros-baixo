package com.jurosbaixo.fizzbuzz.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FizzBuzzClient {

    private String urlBase;
    private String xApiKey;

    public FizzBuzzClient(String urlBase, String xApiKey) {
        this.urlBase = urlBase;
        this.xApiKey = xApiKey;
    }

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();


    public List<Integer> getFizzBuzz() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlBase))
                .setHeader("Content-Type", "application/json")
                .setHeader("X-API-KEY", xApiKey)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Gson gson = new Gson();
           // System.out.println(response.body());
            Type IntegerListType = new TypeToken<ArrayList<Integer>>() {}.getType();
            List<Integer> intList = gson.fromJson(response.body(), IntegerListType);
            return intList;
        } else {
            throw new Exception("get HTTP Status: " +response.statusCode() + " - "+ response.body());
        }
    }

    public boolean postFizzBuzz(String shaHash, String listJson) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(listJson))
                .uri(URI.create(urlBase + "/" + shaHash))
                .setHeader("Content-Type", "application/json")
                .setHeader("X-API-KEY", xApiKey)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            //System.out.println(response.body());
            return true;
        } else {
            throw new Exception("post HTTP Status: " +response.statusCode() + " - "+ response.body());
        }
    }

    public String getFizzBuzzTreasure(String shaHash) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlBase + "/" + shaHash + "/canihastreasure"))
                .setHeader("Content-Type", "application/json")
                .setHeader("X-API-KEY", xApiKey)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
           // System.out.println(response.body());
            Treasure treasure = new Gson().fromJson(response.body(), Treasure.class);
            return treasure.getTreasure();
        } else {
            throw new Exception("treasure HTTP Status: " +response.statusCode() + " - "+ response.body());
        }
    }

    public void resetFizzBuzz() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlBase + "/reset"))
                .setHeader("Content-Type", "application/json")
                .setHeader("X-API-KEY", xApiKey)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("reset HTTP Status: " +response.statusCode() + " - "+ response.body());
        } else {
            //System.out.println(response.body());
        }
    }

    public void deleteFizzBuzz(String shaHash) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(urlBase + "/" + shaHash))
                .setHeader("Content-Type", "application/json")
                .setHeader("X-API-KEY", xApiKey)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("delete HTTP Status: " +response.statusCode() + " - "+ response.body());
        } else {
            //System.out.println(response.body());
        }
    }
}
