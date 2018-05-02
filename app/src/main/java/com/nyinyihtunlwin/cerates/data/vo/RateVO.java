package com.nyinyihtunlwin.cerates.data.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.nyinyihtunlwin.cerates.utils.AppConstants;


@Entity(tableName = AppConstants.TABLE_CER)
public class RateVO {

    @PrimaryKey
    @NonNull
    private String currency;

    private String exchangeRate;


    public RateVO(String currency, String exchangeRate) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
