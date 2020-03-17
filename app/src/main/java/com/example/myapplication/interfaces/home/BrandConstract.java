package com.example.myapplication.interfaces.home;

import com.example.myapplication.interfaces.IBasePersenter;
import com.example.myapplication.interfaces.IBaseView;
import com.example.myapplication.models.bean.BrandBean;
import com.example.myapplication.models.bean.BrandGoodsBean;

public interface BrandConstract {

    interface View extends IBaseView {
        void getBrandInfoReturn(BrandBean result);
        void getBrandGoodsReturn(BrandGoodsBean result);
    }
    interface Persenter extends IBasePersenter<View> {
        void getBrandInfo(String id);
        void getBrandGoods(String brandId, int page, int size);
    }

}
