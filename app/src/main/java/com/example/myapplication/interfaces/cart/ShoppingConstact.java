package com.example.myapplication.interfaces.cart;

import com.example.myapplication.interfaces.IBasePersenter;
import com.example.myapplication.interfaces.IBaseView;
import com.example.myapplication.models.bean.AddressBean;
import com.example.myapplication.models.bean.AdressSaveBean;
import com.example.myapplication.models.bean.CartBean;
import com.example.myapplication.models.bean.CartGoodsCheckBean;
import com.example.myapplication.models.bean.CartGoodsDeleteBean;
import com.example.myapplication.models.bean.CartGoodsUpdateBean;
import com.example.myapplication.models.bean.OrderInfoBean;
import com.example.myapplication.models.bean.RegionBean;

import java.util.Map;

public interface ShoppingConstact {

    interface View extends IBaseView{
        void getCartIndexReturn(CartBean result);
        void setCartGoodsCheckedReturn(CartGoodsCheckBean result);
        void updateCartGoodsReturn(CartGoodsUpdateBean result);
        void deleteCartGoodsReturn(CartGoodsDeleteBean result);
    }

    interface Presenter extends IBasePersenter<View>{
        void getCartIndex();
        void setCartGoodsChecked(String pids,int isChecked);
        void updateCartGoods(String pids,String goodsId,int number,int id);
        void deleteCartGoods(String pids);
    }


    interface OrderView extends IBaseView{
        void getOrderListReturn(OrderInfoBean result);
    }

    interface OrderPresenter extends IBasePersenter<OrderView>{
        void getOrderList(int addressId,int couponId);
    }


    interface AdressView extends IBaseView{
        void getAdressListReturn(AddressBean result);
    }

    interface AdressPresenter extends IBasePersenter<AdressView>{
        void getAdressList();
    }

    interface AdressNewView extends IBaseView{
        void saveAdressReturn(AdressSaveBean result);
        void getRegionReturn(RegionBean result);
    }

    interface AdressNewPresenter extends IBasePersenter<AdressNewView>{
        void saveAdress(Map map);
        void getRegion(int parentId);
    }

}
