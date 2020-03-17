package com.example.myapplication.persenter.sort;

import com.example.myapplication.base.BasePersenter;
import com.example.myapplication.common.CommonSubscriber;
import com.example.myapplication.interfaces.sort.SortConstract;
import com.example.myapplication.models.HttpManager;
import com.example.myapplication.models.bean.SortDetailGoodsBean;
import com.example.myapplication.models.bean.SortDetailTabBean;
import com.example.myapplication.utils.RxUtils;

public class SortDetailPersenter extends BasePersenter<SortConstract.DetailView> implements SortConstract.DetailPersenter {

    @Override
    public void getSortDetailTab(int id) {
       addSubscribe(HttpManager.getInstance().getShopApi().getSortDetailTab(id)
       .compose(RxUtils.<SortDetailTabBean>rxScheduler())
       .subscribeWith(new CommonSubscriber<SortDetailTabBean>(mView){
           @Override
           public void onNext(SortDetailTabBean sortDetailTabBean) {
               mView.getSortDetailTabReturn(sortDetailTabBean);
           }
       }));
    }

    @Override
    public void getSortDetailGoods(int id, int page, int size) {
        addSubscribe(HttpManager.getInstance().getShopApi().getSortDetailGoods(id,page,size)
                .compose(RxUtils.<SortDetailGoodsBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<SortDetailGoodsBean>(mView){
                    @Override
                    public void onNext(SortDetailGoodsBean sortDetailGoodsBean) {
                        mView.getSortDetailGoodsReturn(sortDetailGoodsBean);
                    }
                }));
    }

}
