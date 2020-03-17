package com.example.myapplication.ui.shoping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.interfaces.cart.ShoppingConstact;
import com.example.myapplication.models.bean.OrderInfoBean;
import com.example.myapplication.persenter.cart.OrderPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInfoActivity extends BaseActivity<ShoppingConstact.OrderPresenter> implements ShoppingConstact.OrderView {

    int addressId, couponId = 0;
    @BindView(R.id.txt_noaddress_tips)
    TextView txtNoaddressTips;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.txt_default)
    TextView txtDefault;
    @BindView(R.id.txt_address)
    TextView txtAddress;
    @BindView(R.id.layout_address)
    ConstraintLayout layoutAddress;
    @BindView(R.id.layout_addressInfo)
    ConstraintLayout layoutAddressInfo;
    @BindView(R.id.txt_couponNums)
    TextView txtCouponNums;
    @BindView(R.id.layout_coupon)
    ConstraintLayout layoutCoupon;
    @BindView(R.id.txt_totalPrice)
    TextView txtTotalPrice;
    @BindView(R.id.txt_freight)
    TextView txtFreight;
    @BindView(R.id.txt_couponPrice)
    TextView txtCouponPrice;
    @BindView(R.id.layout_goods_info)
    ConstraintLayout layoutGoodsInfo;
    @BindView(R.id.recy_goodList)
    RecyclerView recyGoodList;
    @BindView(R.id.layout_goodList)
    ConstraintLayout layoutGoodList;
    @BindView(R.id.txt_pay)
    TextView txtPay;
    @BindView(R.id.layout_pay)
    ConstraintLayout layoutPay;

    @Override
    protected int getLayout() {
        return R.layout.activity_orderinfo;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        persenter.getOrderList(addressId, couponId);
    }

    @Override
    protected ShoppingConstact.OrderPresenter createPersenter() {
        return new OrderPresenter();
    }

    @Override
    public void getOrderListReturn(OrderInfoBean result) {

        updateAddress(result.getData().getCheckedAddress());

        updateCoupon(result.getData().getCouponList());

        updateOrderInfo(result.getData());

        updateGoodList(result.getData().getCheckedGoodsList());

    }

    @OnClick({R.id.txt_noaddress_tips, R.id.layout_address, R.id.layout_coupon, R.id.txt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_noaddress_tips:
                Intent intent = new Intent(this,AdressEditorActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_address:
                Intent intent1 = new Intent(this,AdressListActivity.class);
                startActivity(intent1);
                break;
            case R.id.layout_coupon:
                break;
            case R.id.txt_pay:
                break;
        }
    }

    private void updateAddress(OrderInfoBean.DataBean.CheckedAddressBean checkedAddress){

    }

    private void updateCoupon(List<OrderInfoBean.DataBean.CouponListBean> couponList){

    }

    private void updateOrderInfo(OrderInfoBean.DataBean data){

    }

    private void updateGoodList(List<OrderInfoBean.DataBean.CheckedGoodsListBean> checkedGoodsList){

    }


}
