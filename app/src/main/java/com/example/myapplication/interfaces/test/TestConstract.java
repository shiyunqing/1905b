package com.example.myapplication.interfaces.test;

import com.example.myapplication.interfaces.IBasePersenter;
import com.example.myapplication.interfaces.IBaseView;
import com.example.myapplication.models.bean.ChaptersBean;

public interface TestConstract {

    interface View extends IBaseView {
        void getChaptersReturn(ChaptersBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        void getChapters();
    }

}
