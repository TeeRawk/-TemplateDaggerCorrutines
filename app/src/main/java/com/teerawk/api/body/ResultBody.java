package com.teerawk.api.body;

import com.google.gson.annotations.SerializedName;


public class ResultBody<T> {

    @SerializedName("result")
    T result;

    public ResultBody(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

}
