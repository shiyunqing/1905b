package com.example.myapplication.persenter.cart;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.interfaces.cart.ShoppingConstact;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.CartBean;
import com.example.myapplication.models.bean.CartGoodsCheckBean;
import com.example.myapplication.models.bean.CartGoodsDeleteBean;
import com.example.myapplication.models.bean.CartGoodsUpdateBean;
import com.example.myapplication.utils.RxUtils;

import io.reactivex.functions.Function;


public class ShoppingPresenter extends BasePersenter<ShoppingConstact.View> implements ShoppingConstact.Presenter {
    @Override
    public void getCartIndex() {
        addSubscribe(HttpManager.getInstance().getShopApi().getCartIndex()
        .compose(RxUtils.<CartBean>rxScheduler())
        .map(new Function<CartBean, CartBean>() {
            @Override
            public CartBean apply(CartBean cartBean) throws Exception {
                for(CartBean.DataBean.CartListBean item:cartBean.getData().getCartList()){
                    item.isSelect = item.getChecked() == 0 ? true : false;
                }
                return cartBean;
            }
        })
        .subscribeWith(new CommonSubscriber<CartBean>(mView) {
            @Override
            public void onNext(CartBean cartBean) {
                mView.getCartIndexReturn(cartBean);
            }
        }));
    }

    @Override
    public void setCartGoodsChecked(String pids, int isChecked) {
        addSubscribe(HttpManager.getInstance().getShopApi().setCartGoodsCheck(pids,isChecked)
                .compose(RxUtils.<CartGoodsCheckBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartGoodsCheckBean>(mView) {
                    @Override
                    public void onNext(CartGoodsCheckBean cartBean) {
                        mView.setCartGoodsCheckedReturn(cartBean);
                    }
                }));
    }

    @Override
    public void updateCartGoods(String pids, String goodsId, int number, int id) {
        addSubscribe(HttpManager.getInstance().getShopApi().updateCartGoods(pids,goodsId,number,id)
                .compose(RxUtils.<CartGoodsUpdateBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartGoodsUpdateBean>(mView) {
                    @Override
                    public void onNext(CartGoodsUpdateBean updateBean) {
                        mView.updateCartGoodsReturn(updateBean);
                    }
                }));
    }

    @Override
    public void deleteCartGoods(String pids) {
        addSubscribe(HttpManager.getInstance().getShopApi().deleteCartGoods(pids)
                .compose(RxUtils.<CartGoodsDeleteBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartGoodsDeleteBean>(mView) {
                    @Override
                    public void onNext(CartGoodsDeleteBean deleteBean) {
                        mView.deleteCartGoodsReturn(deleteBean);
                    }
                }));
    }
}
