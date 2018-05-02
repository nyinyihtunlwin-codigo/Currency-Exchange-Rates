package com.nyinyihtunlwin.cerates.mvp.presenters;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.nyinyihtunlwin.cerates.data.models.CERModel;
import com.nyinyihtunlwin.cerates.data.vo.RateVO;
import com.nyinyihtunlwin.cerates.mvp.views.CERView;

import java.util.List;

public class CERPresenter extends BasePresenter<CERView> {

    private CERModel mCerModel;
    private LifecycleOwner mLifeCycleOwner;

    public CERPresenter(LifecycleOwner lifecycleOwner, CERModel cerModel) {
        super();
        this.mCerModel = cerModel;
        this.mLifeCycleOwner = lifecycleOwner;
    }

    @Override
    public void onCreate(CERView mView) {
        super.onCreate(mView);

    }

    @Override
    public void onStart() {
        mCerModel.getmRates().observe(mLifeCycleOwner, new Observer<List<RateVO>>() {
            @Override
            public void onChanged(@Nullable List<RateVO> rateList) {
                if (rateList != null && rateList.size() > 0) {
                    mView.displayExchangeRates(rateList);
                } else {
                    mView.showLoading();
                    startLoadingRates(mLifeCycleOwner);
                }

            }
        });
    }


    public void startLoadingRates(LifecycleOwner lifecycleOwner) {
        mCerModel.loadRates().observe(lifecycleOwner, new Observer<List<RateVO>>() {
            @Override
            public void onChanged(@Nullable List<RateVO> rateList) {
                mView.displayExchangeRates(rateList);
            }
        });
    }

    @Override
    public void onStop() {
    }

}
