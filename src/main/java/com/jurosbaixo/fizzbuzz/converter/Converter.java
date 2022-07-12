package com.jurosbaixo.fizzbuzz.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converter {

    /**
     *  Converts a List of string into equivalent json
     * @param list
     * @return json 
     */
    public static String toJson(List<String> list) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().toJson(list, listType);
    }
}
