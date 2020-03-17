package com.example.myapplication.persenter.home;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.interfaces.home.HomeConstract;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.IndexBean;
import com.example.myapplication.utils.RxUtils;

public class HomePersenter extends BasePersenter<HomeConstract.View> implements HomeConstract.Persenter {

    @Override
    public void getHomeData() {
        addSubscribe(HttpManager.getInstance().getShopApi().getIndexData()
        .compose(RxUtils.<IndexBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<IndexBean>(mView){

            @Override
            public void onNext(IndexBean indexBean) {
                mView.getHomeDataReturn(indexBean);
            }
        }));
    }

}
