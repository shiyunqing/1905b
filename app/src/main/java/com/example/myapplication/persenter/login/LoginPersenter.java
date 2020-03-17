package com.example.myapplication.persenter.login;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.interfaces.login.LoginConstract;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.UserBean;
import com.example.myapplication.utils.RxUtils;

public class LoginPersenter extends BasePersenter<LoginConstract.View> implements LoginConstract.Persenter {
    @Override
    public void login(String nickname, String password) {
        addSubscribe(HttpManager.getInstance().getShopApi().login(nickname,password)
        .compose(RxUtils.<UserBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<UserBean>(mView){

            @Override
            public void onNext(UserBean userBean) {
                if(userBean.getErrno() == 0){
                    mView.loginReturn(userBean);
                }else{
                    mView.showTips(userBean.getErrmsg());
                }
            }
        }));
    }
}
