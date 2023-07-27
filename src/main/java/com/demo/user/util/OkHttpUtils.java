package com.demo.user.util;

import okhttp3.*;

import lombok.NonNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OkHttpUtils {

    /**
     * GET Method begin---------------------------------
     */

    public static <T> T get(@NonNull String url, Class<T> clasz) {
        return get(url, null, null, clasz);
    }

    public static <T> List<T> getList(@NonNull String url, Class<T> clasz) throws Exception {
        return getList(url, null, null, clasz);
    }


    public static void get(@NonNull String url, Callback callback) {
        get(url, null, null, callback);
    }

    public static <T> T get(@NonNull String url, Map<String, String> queryParameter, Class<T> clasz) {
        return get(url, null, queryParameter, clasz);
    }

    public static <T> List<T> getList(@NonNull String url, Map<String, String> queryParameter, Class<T> clasz) {
        return getList(url, null, queryParameter, clasz);
    }
    public static void get(@NonNull String url, Map<String, String> queryParameter, Callback callback) {
        get(url, null, queryParameter, callback);
    }

    public static <T> T get(@NonNull String url, Map<String, String> headerParameter, Map<String, String> queryParameter, Class<T> clasz) {
        Request request = processGetParameter(url, headerParameter, queryParameter);

        try (Response resp = new OkHttpClient().newCall(request).execute();) {
            return processResponse(resp, clasz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> getList(@NonNull String url, Map<String, String> headerParameter, Map<String, String> queryParameter, Class<T> clasz) {
        Request request = processGetParameter(url, headerParameter, queryParameter);

        try (Response resp = new OkHttpClient().newCall(request).execute();) {
            return processListResponse(resp, clasz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void get(@NonNull String url, Map<String, String> headerParameter, Map<String, String> queryParameter, Callback callback) {
        Request request = processGetParameter(url, headerParameter, queryParameter);
        new OkHttpClient().newCall(request).enqueue(callback);
    }

    private static Request processGetParameter(String url, Map<String, String> headerParameter, Map<String, String> queryParameter) {
        Request.Builder builder = new Request.Builder();
        if (!isEmptyMap(headerParameter)) {
            builder.headers(Headers.of(headerParameter));
        }
        if (isEmptyMap(queryParameter)) {
            builder.url(url);
        } else {
            boolean hasQuery = false;
            try {
                hasQuery = new URL(url).getQuery().isEmpty();
            } catch (MalformedURLException e) {
                throw new RuntimeException("url is illegal");
            }
            StringBuilder sb = new StringBuilder(url);
            if (!hasQuery) {
                sb.append("?1=1");
            }
            queryParameter.forEach((k, v) -> {
                sb.append("&").append(k).append("=").append(v);
            });
            builder.url(sb.toString());
        }
        return builder.build();
    }

    private static boolean isEmptyMap(Map<?, ?> map) {
        return Objects.isNull(map) || map.isEmpty();
    }

    @SuppressWarnings("unchecked")
    private static <T> T processResponse(Response resp, Class<T> clasz) throws IOException {
        if (resp.isSuccessful()) {
            if (Objects.equals(Void.class, clasz)) {
                return null;
            }
            String respStr = resp.body().string();
            if(Objects.equals(String.class, clasz)) {
                return (T)respStr;
            }
            return GsonUtil.GsonToBean(respStr, clasz);
        }
        return null;
    }

    private static <T> List<T> processListResponse(Response resp, Class<T> clasz) throws IOException {
        if (resp.isSuccessful()) {
            if (Objects.equals(Void.class, clasz)) {
                return null;
            }
            String respStr = resp.body().string();
            return GsonUtil.jsonToList(respStr, clasz);
        }
        return null;
    }

}
