package com.example.myapplication.persenter.cart;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.interfaces.cart.ShoppingConstact;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.AddressBean;
import com.example.myapplication.utils.RxUtils;

public class AdressPresenter extends BasePersenter<ShoppingConstact.AdressView> implements ShoppingConstact.AdressPresenter {

    @Override
    public void getAdressList() {
        addSubscribe(HttpManager.getInstance().getShopApi().getAddress()
        .compose(RxUtils.<AddressBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<AddressBean>(mView){

            @Override
            public void onNext(AddressBean addressBean) {
                mView.getAdressListReturn(addressBean);
            }
        }));
    }
}
