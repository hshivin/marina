package com.hivin.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class JsonUtil {
    static List<String> list = new ArrayList<String>();

    public static <T> String objectToJson(Object object) {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat(Dict.FormatDate.YMDHMS);
        Gson gson = builder.create();
        String jsonStr = gson.toJson(object);
        return jsonStr;
    }


    public static <T> String mapToJson(Map<String, T> map) {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat(Dict.FormatDate.YMDHMS);
        Gson gson = builder.create();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }

    public static <T> String linkMapToJson(LinkedHashMap<String, T> map) {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat(Dict.FormatDate.YMDHMS);
        Gson gson = builder.create();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }

    public static Map<String, Object> jsonToMap(String json) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
                .create();
        Map<String, Object> retMap2 = gson.fromJson(json,
                new TypeToken<Map<String, String>>() {
                }.getType());
        return retMap2;
    }

    public static Map<String, Object> jsonToLinkHashMap(String json) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
                .create();
        Map<String, Object> retMap2 = gson.fromJson(json,
                new TypeToken<LinkedHashMap<String, String>>() {
                }.getType());
        return retMap2;
    }

    /**
     * diff two json
     *
     * @param json1
     * @param json2
     * @return
     */
    public static boolean diffTowJson(String json1, String json2) {
        boolean flag = false;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode1 = null;
        JsonNode jsonNode2 = null;
        try {
            jsonNode1 = objectMapper.readTree(json1);
            jsonNode2 = objectMapper.readTree(json2);
        } catch (IOException e) {
            return flag;
        }
        JsonNode diff = JsonDiff.asJson(jsonNode1, jsonNode2);
        if (diff.size() == 0) flag = true;
        return flag;
    }

}

