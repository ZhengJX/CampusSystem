package com.saineng.campussystem.common;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by mop on 16/1/9.
 */
public class DataDeserializer <T> implements JsonDeserializer<T> {

    private Class<T> tClass;

    public DataDeserializer(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement content = json.getAsJsonObject().get("data");
        return new Gson().fromJson(content, tClass);
    }
}
