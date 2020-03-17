package com.example.myapplication.interfaces.sort;

import com.example.myapplication.interfaces.IBasePersenter;
import com.example.myapplication.interfaces.IBaseView;
import com.example.myapplication.models.bean.SortBean;
import com.example.myapplication.models.bean.SortDetailGoodsBean;
import com.example.myapplication.models.bean.SortDetailTabBean;
import com.example.myapplication.models.bean.SortGoodsBean;

public interface SortConstract {

    interface View extends IBaseView {
        void getSortReturn(SortBean result);
        void getCurrentSortDataReturn(SortGoodsBean result);
    }
    interface Persenter extends IBasePersenter<View> {
        void getSort();
        void getCurrentSortData(int id);
    }
    interface DetailView extends IBaseView{
        void getSortDetailTabReturn(SortDetailTabBean result);
        void getSortDetailGoodsReturn(SortDetailGoodsBean result);
    }
    interface DetailPersenter extends IBasePersenter<DetailView>{
        void getSortDetailTab(int id);
        void getSortDetailGoods(int id, int page, int size);
    }



}
