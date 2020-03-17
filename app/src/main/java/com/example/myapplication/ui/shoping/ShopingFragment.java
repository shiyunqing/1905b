package com.example.myapplication.ui.shoping;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.interfaces.cart.ShoppingConstact;
import com.example.myapplication.models.bean.CartBean;
import com.example.myapplication.models.bean.CartGoodsCheckBean;
import com.example.myapplication.models.bean.CartGoodsDeleteBean;
import com.example.myapplication.models.bean.CartGoodsUpdateBean;
import com.example.myapplication.persenter.cart.ShoppingPresenter;
import com.example.myapplication.ui.login.LoginActivity;
import com.example.myapplication.ui.shoping.adapters.ShoppingAdapter;
import com.example.myapplication.utils.SpUtils;
import com.example.myapplication.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopingFragment extends BaseFragment<ShoppingConstact.Presenter> implements ShoppingConstact.View,
        BaseAdapter.ItemClickHandler {
    @BindView(R.id.radio_all)
    CheckBox radioAll;
    @BindView(R.id.txt_TotalPrice)
    TextView txtTotalPrice;
    @BindView(R.id.txt_order)
    TextView txtOrder;
    @BindView(R.id.txt_edit)
    TextView txtEdit;
    @BindView(R.id.layout_common)
    ConstraintLayout layoutCommon;
    @BindView(R.id.cart_list)
    RecyclerView cartList;

    ShoppingAdapter shoppingAdapter;
    List<CartBean.DataBean.CartListBean> list;
    @Override
    protected int getLayout() {
        return R.layout.fragment_shoping;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        shoppingAdapter = new ShoppingAdapter(list,context);
        cartList.setLayoutManager(new LinearLayoutManager(context));
        cartList.setAdapter(shoppingAdapter);
        shoppingAdapter.setOnItemClickHandler(this);
    }

    @Override
    protected void initData() {
        String token = SpUtils.getInstance().getString("token");
        if (TextUtils.isEmpty(token)) {
            Intent intent = new Intent(context, LoginActivity.class);
            startActivityForResult(intent,100);
        } else {
            persenter.getCartIndex();
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if(requestCode == 100){
            if(persenter != null) persenter.getCartIndex();
        }
    }

    @Override
    protected ShoppingConstact.Presenter createPersenter() {
        return new ShoppingPresenter();
    }

    @Override
    public void getCartIndexReturn(CartBean result) {
        shoppingAdapter.updata(result.getData().getCartList());
        int totalPrice = 0;
        int nums = 0;
        boolean isSelectAll = true;
        for(CartBean.DataBean.CartListBean item:result.getData().getCartList()){
            if(isSelectAll){
                if(!item.isSelect){
                    isSelectAll = false;
                }
            }
            if(item.isSelect){
                totalPrice += item.getRetail_price()*item.getNumber();
                nums += item.getNumber();
            }
        }
        if(isSelectAll){
            radioAll.setChecked(true);
        }
        String price = context.getResources().getString(R.string.price_news_model).replace("$",String.valueOf(totalPrice));
        txtTotalPrice.setText(price);
        radioAll.setText("全选("+nums+")");
    }

    @Override
    public void setCartGoodsCheckedReturn(CartGoodsCheckBean result) {

    }

    @Override
    public void updateCartGoodsReturn(CartGoodsUpdateBean result) {
        for(CartGoodsUpdateBean.DataBean.CartListBean item:result.getData().getCartList()){
            CartBean.DataBean.CartListBean bean = getItemDataById(item.getId());
            if(bean != null) bean.setNumber(item.getNumber());
        }
        shoppingAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteCartGoodsReturn(CartGoodsDeleteBean result) {
       // int lg = list.size();
        for(int i=0; i<list.size(); i++){
            CartBean.DataBean.CartListBean listBean = list.get(i);
            boolean bool = false;
            for(CartGoodsDeleteBean.DataBean.CartListBean item:result.getData().getCartList()){
                if(item.getId() == listBean.getId()){
                    bool = true;
                    break;
                }
            }
            if(!bool){
                list.remove(i);
            }
        }
        shoppingAdapter.notifyDataSetChanged();
    }

    private CartBean.DataBean.CartListBean getItemDataById(int id){
        for(CartBean.DataBean.CartListBean item:list){
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }

    @OnClick({R.id.txt_order,R.id.txt_edit,R.id.radio_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio_all:
                boolean isChecked = radioAll.isChecked();
                setSelectAll(isChecked);
                shoppingAdapter.notifyDataSetChanged();
                break;
            case R.id.txt_order:
                boolean isEditor = getPageIsEditor();
                if(isEditor){
                    deleteGoods();
                }else{
                    doOrder();
                }
                break;
            case R.id.txt_edit:
                boolean bool = getPageIsEditor();
                if(!bool){
                    txtEdit.setText("完成");
                    txtOrder.setText("删除所选");
                    shoppingAdapter.isEdit = true;
                    shoppingAdapter.notifyDataSetChanged();
                }else{
                    txtEdit.setText("编辑");
                    txtOrder.setText("下单");
                    shoppingAdapter.isEdit = false;
                    shoppingAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
    private void deleteGoods(){
        StringBuilder sb = new StringBuilder();
        for(CartBean.DataBean.CartListBean item:list){
            if(item.isDelSelect){
                sb.append(item.getProduct_id());
                sb.append(",");
            }
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length()-1);
            String pids = sb.toString();
            persenter.deleteCartGoods(pids);
        }else{
            showTips("没有选中任何商品");
        }
    }
    private void doOrder(){

    }
    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
        boolean bool = getPageIsEditor();
        if(!bool){
            updateSelectAll();
            int[] ids = new int[1];
            ids[0] = list.get(position).getId();
            int ischecked = list.get(position).isSelect ? 0 : 1;
            updateGoodsChecked(ids,ischecked);
        }else{
            String pids = String.valueOf(list.get(position).getProduct_id());
            String goodsId = String.valueOf(list.get(position).getGoods_id());
            int number = list.get(position).getNumber();
            int id = list.get(position).getId();
            persenter.updateCartGoods(pids,goodsId,number,id);
        }
    }

    private void updateGoodsChecked(int[] ids,int isChecked){
        String pids = StringUtils.splitArray(ids);
        persenter.setCartGoodsChecked(pids,isChecked);
    }

    private void setSelectAll(boolean bool){
        int totalPrice = 0;
        int nums = 0;
        int[] ids = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            list.get(i).isSelect = bool;
            ids[i] = list.get(i).getId();
            if(bool){
                totalPrice += list.get(i).getRetail_price()*list.get(i).getNumber();
                nums += list.get(i).getNumber();
            }
        }
        int isChecked = bool ? 0 : 1;
        updateGoodsChecked(ids,isChecked);
        if(bool){
            String  price = context.getResources().getString(R.string.price_news_model).replace("$",String.valueOf(totalPrice));
            txtTotalPrice.setText(price);
            radioAll.setText("全选("+nums+")");
        }else{
            txtTotalPrice.setText("");
        }

    }

    private void updateSelectAll(){
        int totalPrice = 0;
        int nums = 0;
        boolean isAll = true;
        for(int i=0; i<list.size(); i++){
            if(!shoppingAdapter.isEdit){
                boolean isSelect = list.get(i).isSelect;
                if(isAll){
                    isAll = isSelect;
                }
                if(isSelect){
                    nums += list.get(i).getNumber();
                    totalPrice += list.get(i).getNumber()*list.get(i).getRetail_price();
                }
            }else{
                boolean isDelSelect = list.get(i).isDelSelect;
                if(isAll){
                    isAll = isDelSelect;
                }
                if(isDelSelect){
                    nums += list.get(i).getNumber();
                }
            }
        }
        radioAll.setChecked(isAll);
        radioAll.setText("全选("+nums+")");
        if(!shoppingAdapter.isEdit){
            String price = context.getResources().getString(R.string.price_news_model);
            price = price.replace("$",String.valueOf(totalPrice));
            txtTotalPrice.setText(price);
        }else{
            txtTotalPrice.setText("");
        }
    }
    private boolean getPageIsEditor(){
        String str = txtEdit.getText().toString();
        return str.equals("编辑") ? false : true;
    }

}
