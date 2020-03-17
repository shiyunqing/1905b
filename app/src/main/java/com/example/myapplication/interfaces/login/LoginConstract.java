package com.example.myapplication.interfaces.login;

import com.example.myapplication.interfaces.IBasePersenter;
import com.example.myapplication.interfaces.IBaseView;
import com.example.myapplication.models.bean.UserBean;

public interface LoginConstract {

    interface View extends IBaseView {
        void loginReturn(UserBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        void login(String nickname, String password);
    }

}
