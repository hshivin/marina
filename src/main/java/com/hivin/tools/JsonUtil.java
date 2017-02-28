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


    public static void getAllOrder(String[] array, int begin, int end) {
        if (begin == end) {
            String arrStr = "";
            for (int i = 0; i < array.length; i++) {
                arrStr += array[i];
            }
            list.add(arrStr);
        } else {
            for (int i = begin; i <= end; i++) {
                // 交换数据
                swap(array, begin, i);
                getAllOrder(array, begin + 1, end);
                swap(array, i, begin);
            }
        }
    }

    public static void swap(String[] array, int from, int to) {
        // 这里应该加上各种防止无效交换的情况
        // 比如位置相同，或者2个位置的数据相同
        if (from == to) {
            return;
        }
        String tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }

    /**
     * diff two json
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

