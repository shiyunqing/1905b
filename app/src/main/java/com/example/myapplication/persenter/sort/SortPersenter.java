package com.example.myapplication.persenter.sort;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.interfaces.sort.SortConstract;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.SortBean;
import com.example.myapplication.models.bean.SortGoodsBean;
import com.example.myapplication.utils.RxUtils;

public class SortPersenter extends BasePersenter<SortConstract.View> implements SortConstract.Persenter {

    @Override
    public void getSort() {
        addSubscribe(HttpManager.getInstance().getShopApi().getSort()
        .compose(RxUtils.<SortBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<SortBean>(mView) {
            @Override
            public void onNext(SortBean sortBean) {
                mView.getSortReturn(sortBean);
            }
        }));
    }

    @Override
    public void getCurrentSortData(int id) {
        addSubscribe(HttpManager.getInstance().getShopApi().getCurrentSortData(id)
                .compose(RxUtils.<SortGoodsBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<SortGoodsBean>(mView) {
                    @Override
                    public void onNext(SortGoodsBean sortGoodsBean) {
                        mView.getCurrentSortDataReturn(sortGoodsBean);
                    }
                }));
    }
}
