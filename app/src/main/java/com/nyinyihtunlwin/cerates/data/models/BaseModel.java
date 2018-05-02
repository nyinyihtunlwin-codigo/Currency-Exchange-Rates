package com.nyinyihtunlwin.cerates.data.models;

import android.arch.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nyinyihtunlwin.cerates.data.db.AppDatabase;
import com.nyinyihtunlwin.cerates.network.CERApi;
import com.nyinyihtunlwin.cerates.utils.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseModel extends ViewModel{
    protected CERApi mCerApi;

    protected AppDatabase mAppDatabase;


    protected void initTedTalksApi() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        mCerApi = retrofit.create(CERApi.class);
    }
}
