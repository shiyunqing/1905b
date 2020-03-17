package com.example.myapplication.persenter.cart;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.interfaces.cart.ShoppingConstact;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.OrderInfoBean;
import com.example.myapplication.utils.RxUtils;

public class OrderPresenter extends BasePersenter<ShoppingConstact.OrderView> implements ShoppingConstact.OrderPresenter {

    @Override
    public void getOrderList(int addressId, int couponId) {
        addSubscribe(HttpManager.getInstance().getShopApi().getOrderInfo(addressId,couponId)
        .compose(RxUtils.<OrderInfoBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<OrderInfoBean>(mView){

            @Override
            public void onNext(OrderInfoBean orderInfoBean) {
                mView.getOrderListReturn(orderInfoBean);
            }
        }));
    }
}
