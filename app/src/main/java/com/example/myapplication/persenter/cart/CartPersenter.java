package com.example.myapplication.persenter.cart;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.interfaces.cart.CartConstart;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.RelatedBean;
import com.example.myapplication.utils.RxUtils;

public class CartPersenter extends BasePersenter<CartConstart.View> implements CartConstart.Persenter {

    @Override
    public void getRelatedData(int id) {
        addSubscribe(HttpManager.getInstance().getShopApi().getRelatedData(id)
        .compose(RxUtils.<RelatedBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<RelatedBean>(mView){

            @Override
            public void onNext(RelatedBean relatedBean) {
                mView.getRelatedDataReturn(relatedBean);
            }
        }));
    }

}
