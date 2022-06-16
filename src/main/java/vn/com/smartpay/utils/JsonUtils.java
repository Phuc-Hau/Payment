package vn.com.smartpay.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import vn.com.smartpay.model.GoodDetails;

public class JsonUtils {
    private static final Gson gson = new Gson();
    private static final JsonParser jsonParser = new JsonParser();

    public static String toString(Object object) {
        return gson.toJson(object);
    }

    public static <T> T toJson(String json, Class<T> object) {
        return gson.fromJson(json, object);
    }

}
