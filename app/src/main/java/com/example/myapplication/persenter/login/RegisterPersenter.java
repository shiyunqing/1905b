package com.example.myapplication.persenter.login;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.interfaces.login.RegisterConstract;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.VerifyBean;
import com.example.myapplication.utils.RxUtils;

public class RegisterPersenter extends BasePersenter<RegisterConstract.View> implements RegisterConstract.Persenter {
    @Override
    public void getVerify() {
        addSubscribe(HttpManager.getInstance().getShopApi().getVerify()
        .compose(RxUtils.<VerifyBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<VerifyBean>(mView){

            @Override
            public void onNext(VerifyBean verifyBean) {
                mView.getVerifyReturn(verifyBean);
            }
        }));
    }

}
