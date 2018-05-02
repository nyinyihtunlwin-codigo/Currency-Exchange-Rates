package com.nyinyihtunlwin.cerates.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nyinyihtunlwin.cerates.R;
import com.nyinyihtunlwin.cerates.data.models.CERModel;
import com.nyinyihtunlwin.cerates.data.vo.RateVO;
import com.nyinyihtunlwin.cerates.mvp.presenters.CERPresenter;
import com.nyinyihtunlwin.cerates.mvp.views.CERView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CERView {

    private CERModel mCerModel;
    private CERPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCerModel = ViewModelProviders.of(this).get(CERModel.class);
        mCerModel.initDatabase(getApplicationContext());
        mPresenter = new CERPresenter(this, mCerModel);
        mPresenter.onCreate(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void displayExchangeRates(List<RateVO> rateList) {

    }

    @Override
    public void showLoading() {

    }
}
