package com.example.myapplication.persenter.home;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.interfaces.home.BrandConstract;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.BrandBean;
import com.example.myapplication.models.bean.BrandGoodsBean;
import com.example.myapplication.utils.RxUtils;

public class BrandPersenter extends BasePersenter<BrandConstract.View> implements BrandConstract.Persenter {


    @Override
    public void getBrandInfo(String id) {
        addSubscribe(HttpManager.getInstance().getShopApi().getBrandInfo(id)
        .compose(RxUtils.<BrandBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<BrandBean>(mView){

            @Override
            public void onNext(BrandBean brandBean) {
                mView.getBrandInfoReturn(brandBean);
            }
        }));
    }

    @Override
    public void getBrandGoods(String brandId, int page, int size) {
        addSubscribe(HttpManager.getInstance().getShopApi().getBrandGoods(brandId,page,size)
                .compose(RxUtils.<BrandGoodsBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandGoodsBean>(mView){

                    @Override
                    public void onNext(BrandGoodsBean brandGoodsBean) {
                        mView.getBrandGoodsReturn(brandGoodsBean);
                    }
                }));
    }
}
