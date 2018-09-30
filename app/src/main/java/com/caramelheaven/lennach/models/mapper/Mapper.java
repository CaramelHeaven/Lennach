package com.caramelheaven.lennach.models.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

public abstract class Mapper<T1, T2> {
    public abstract T2 map(T1 value);

    public List<T2> map(List<T1> values) {
        List<T2> returnValues = new ArrayList<>(values.size());
        for (T1 value : values) {
            returnValues.add(map(value));
        }
        return returnValues;
    }
}
