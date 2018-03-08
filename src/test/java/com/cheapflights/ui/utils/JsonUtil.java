package com.cheapflights.ui.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonUtil {

    private JsonUtil(){}

    private static class JsonUtilHolder{
        private final static JsonUtil INSTANCE = new JsonUtil();
    }

    public static JsonUtil getInstance(){
        return JsonUtilHolder.INSTANCE;
    }

    public <T> T readJson(String fileName, Class<T> c) {
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(fileName));
            return new Gson().fromJson(jsonReader, c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
