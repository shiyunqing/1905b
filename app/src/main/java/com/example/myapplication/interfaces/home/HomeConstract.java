package com.example.myapplication.interfaces.home;

import com.example.myapplication.interfaces.IBasePersenter;
import com.example.myapplication.interfaces.IBaseView;
import com.example.myapplication.models.bean.IndexBean;

public interface HomeConstract {

    interface View extends IBaseView {
        void getHomeDataReturn(IndexBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        void getHomeData();
    }

}
