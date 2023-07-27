package com.demo.user.util;

import com.demo.user.adapter.DoubleTypeAdapter;
import com.demo.user.adapter.IntegerTypeAdapter;
import com.demo.user.adapter.LongTypeAdapter;
import com.demo.user.model.ParameterizedTypeImpl;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GsonUtil {
    private static Gson gson = null;

    static {
        gson = new GsonBuilder().registerTypeAdapter(Long.class, new LongTypeAdapter())
                .registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
                .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                .create();

    }

    private GsonUtil() {
    }

    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        List<T> list = new ArrayList<>();
        JsonArray array = JsonParser.parseString(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    public static <T, E> T GsonToGenericsBean(String jsonData, Class<T> clazz, Class<E> generClass) {
        Type type = new ParameterizedTypeImpl(clazz, new Class[] { generClass });
        return gson.fromJson(jsonData, type);
    }
}
