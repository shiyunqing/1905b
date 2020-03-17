package com.example.myapplication.persenter.cart;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.interfaces.cart.ShoppingConstact;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.AddressBean;
import com.example.myapplication.models.bean.AdressSaveBean;
import com.example.myapplication.models.bean.RegionBean;
import com.example.myapplication.utils.RxUtils;

import java.util.Map;

public class AdressNewPresenter extends BasePersenter<ShoppingConstact.AdressNewView> implements ShoppingConstact.AdressNewPresenter{


    @Override
    public void saveAdress(Map map) {
        addSubscribe(HttpManager.getInstance().getShopApi().saveAdress(map)
                .compose(RxUtils.<AdressSaveBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<AdressSaveBean>(mView){

                    @Override
                    public void onNext(AdressSaveBean saveBean) {
                        mView.saveAdressReturn(saveBean);
                    }
                }));
    }

    @Override
    public void getRegion(int parentId) {
        addSubscribe(HttpManager.getInstance().getShopApi().getRegion(parentId)
                .compose(RxUtils.<RegionBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<RegionBean>(mView){

                    @Override
                    public void onNext(RegionBean regionBean) {
                        mView.getRegionReturn(regionBean);
                    }
                }));
    }
}
