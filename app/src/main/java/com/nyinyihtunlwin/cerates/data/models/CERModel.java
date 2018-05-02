package com.nyinyihtunlwin.cerates.data.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.nyinyihtunlwin.cerates.CERApp;
import com.nyinyihtunlwin.cerates.data.db.AppDatabase;
import com.nyinyihtunlwin.cerates.data.vo.RateVO;
import com.nyinyihtunlwin.cerates.network.responses.CERResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CERModel extends BaseModel {

    private MutableLiveData<List<RateVO>> mRates;

    public CERModel() {
        mRates = new MutableLiveData<>();
        initTedTalksApi();
    }


    public void initDatabase(Context context) {
        mAppDatabase = AppDatabase.getDatabase(context);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        AppDatabase.destroyInstance();
    }

    public LiveData<List<RateVO>> loadRates() {
        Observable<CERResponse> cerResponseObservable = mCerApi.loadCER();
        cerResponseObservable
                .subscribeOn(Schedulers.io()) //run value creation code on a specific thread (non-UI thread)
                .observeOn(AndroidSchedulers.mainThread()) //observe the emitted value of the Observable on an appropriate thread
                .subscribe(new Observer<CERResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CERResponse getCerResponse) {
                        if (getCerResponse.getRates() != null && getCerResponse.getRates().size() > 0) {


                            List<RateVO> rateList = new ArrayList<>();
                            String[] keys = getCerResponse.getRates().keySet().toArray(new String[0]);
                            String[] values = getCerResponse.getRates().values().toArray(new String[0]);
                            for (int i = 0; i < keys.length; i++) {
                                RateVO rate = new RateVO(keys[i], values[i]);
                                rateList.add(rate);
                            }

                            mRates.setValue(rateList);

                            mAppDatabase.cerDao().deleteAll();
                            long[] insertedIds = mAppDatabase.cerDao().insertCERates(rateList);
                            Log.d(CERApp.TAG, "Total inserted count : " + insertedIds.length);


                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("onError", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return mRates;
    }

    public LiveData<List<RateVO>> getmRates() {
        return mAppDatabase.cerDao().getCERates();
    }
}
