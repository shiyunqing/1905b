package com.example.myapplication.interfaces.login;

import com.example.myapplication.interfaces.IBasePersenter;
import com.example.myapplication.interfaces.IBaseView;
import com.example.myapplication.models.bean.VerifyBean;

public interface RegisterConstract {

    interface View extends IBaseView {
        void getVerifyReturn(VerifyBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        void getVerify();
    }

}
