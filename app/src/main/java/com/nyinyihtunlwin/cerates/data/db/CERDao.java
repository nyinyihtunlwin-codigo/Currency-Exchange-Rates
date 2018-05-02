package com.nyinyihtunlwin.cerates.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.nyinyihtunlwin.cerates.data.vo.RateVO;
import com.nyinyihtunlwin.cerates.utils.AppConstants;

import java.util.List;

@Dao
public interface CERDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCERate(RateVO rateVO);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertCERates(List<RateVO> rateList);

    @Query("SELECT * FROM " + AppConstants.TABLE_CER)
    LiveData<List<RateVO>> getCERates();

    @Query("DELETE FROM " + AppConstants.TABLE_CER)
    void deleteAll();
}
