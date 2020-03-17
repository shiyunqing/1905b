package com.example.myapplication.persenter.test;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.interfaces.test.TestConstract;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.ChaptersBean;
import com.example.myapplication.utils.RxUtils;

import io.reactivex.subscribers.ResourceSubscriber;

public class TestPersenter extends BasePersenter<TestConstract.View> implements TestConstract.Persenter {

    @Override
    public void getChapters() {
        addSubscribe(HttpManager.getInstance().getWanApi().getChapters()
        .compose(RxUtils.<ChaptersBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<ChaptersBean>(mView){

            @Override
            public void onNext(ChaptersBean chaptersBean) {
                mView.getChaptersReturn(chaptersBean);
            }
        }));
    }
}
