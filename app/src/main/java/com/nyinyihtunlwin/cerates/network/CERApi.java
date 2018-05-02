package com.nyinyihtunlwin.cerates.network;

import com.nyinyihtunlwin.cerates.network.responses.CERResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CERApi {

    @GET("latest")
    Observable<CERResponse> loadCER();

}
