package com.example.myapplication.interfaces.cart;

import com.example.myapplication.interfaces.IBasePersenter;
import com.example.myapplication.interfaces.IBaseView;
import com.example.myapplication.models.bean.RelatedBean;

public interface CartConstart {

    interface View extends IBaseView {
        void getRelatedDataReturn(RelatedBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        void getRelatedData(int id);
    }

}
