package com.nyinyihtunlwin.cerates.mvp.views;

import com.nyinyihtunlwin.cerates.data.vo.RateVO;

import java.util.List;

public interface CERView {
    void displayExchangeRates(List<RateVO> rateList);
    void showLoading();
}
