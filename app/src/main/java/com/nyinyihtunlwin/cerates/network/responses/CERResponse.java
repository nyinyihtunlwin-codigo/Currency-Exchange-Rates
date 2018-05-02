package com.nyinyihtunlwin.cerates.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class CERResponse {

    @SerializedName("info")
    private String info;

    @SerializedName("description")
    private String description;

    @SerializedName("timestamp")
    private String timeStamp;

    @SerializedName("rates")
    private HashMap<String, String> rates;

    public void setInfo(String info) {
        this.info = info;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, String> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, String> rates) {
        this.rates = rates;
    }
}
