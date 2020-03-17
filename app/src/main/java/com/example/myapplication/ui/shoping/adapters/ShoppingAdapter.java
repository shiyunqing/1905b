package com.example.myapplication.ui.shoping.adapters;

import android.content.Context;
import android.provider.Settings;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.models.bean.CartBean;

import java.util.List;

public class ShoppingAdapter extends BaseAdapter {

    public boolean isEdit;

    public ShoppingAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_shopping_item;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        ConstraintLayout layout_default = (ConstraintLayout) holder.getView(R.id.layout_default);
        ConstraintLayout layout_edit = (ConstraintLayout) holder.getView(R.id.layout_edit);
        layout_default.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        layout_edit.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        CartBean.DataBean.CartListBean listBean = (CartBean.DataBean.CartListBean) o;
        CheckBox radio_select = (CheckBox) holder.getView(R.id.radio_select);
        ImageView img_item = (ImageView) holder.getView(R.id.img_item);
        TextView txt_name = (TextView) holder.getView(R.id.txt_name);
        TextView txt_price = (TextView) holder.getView(R.id.txt_price);
        TextView txt_nums = (TextView) holder.getView(R.id.txt_nums);
        TextView txt_edit_price = (TextView) holder.getView(R.id.txt_edit_price);
        TextView txt_subtract = (TextView) holder.getView(R.id.txt_subtract);
        TextView txt_add = (TextView) holder.getView(R.id.txt_add);
        TextView txt_edit_nums = (TextView) holder.getView(R.id.txt_edit_nums);
        Glide.with(mContext).load(listBean.getList_pic_url()).into(img_item);
        radio_select.setTag(o);
        radio_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartBean.DataBean.CartListBean bean = (CartBean.DataBean.CartListBean) v.getTag();
                boolean isChecked = radio_select.isChecked();
                if(!isEdit){
                    bean.isSelect = isChecked;
                    if(itemClickHandler != null){
                        itemClickHandler.itemClick(holder.getLayoutPosition(),holder);
                    }
                }else{
                    bean.isDelSelect = isChecked;
                }
            }
        });

        if(!isEdit){
            radio_select.setChecked(listBean.isSelect);
            txt_name.setText(listBean.getGoods_name());
            String price = mContext.getResources().getString(R.string.price_news_model);
            price = price.replace("$",String.valueOf(listBean.getRetail_price()));
            txt_price.setText(price);
            txt_nums.setText(String.valueOf(listBean.getNumber()));

        }else{
            radio_select.setChecked(false);
            String price = mContext.getResources().getString(R.string.price_news_model);
            price = price.replace("$",String.valueOf(listBean.getRetail_price()));
            txt_edit_price.setText(price);
            txt_edit_nums.setText(String.valueOf(listBean.getNumber()));
            txt_subtract.setTag(holder);
            txt_subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listBean.getNumber() == 1) return;
                    listBean.setNumber(listBean.getNumber()-1);
                    BaseViewHolder vh = (BaseViewHolder) v.getTag();
                    int pos = vh.getLayoutPosition();
                    if(itemClickHandler != null){
                        itemClickHandler.itemClick(pos,vh);
                    }
                }
            });
            txt_add.setTag(holder);
            txt_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listBean.setNumber(listBean.getNumber()+1);
                    BaseViewHolder vh = (BaseViewHolder) v.getTag();
                    int pos = vh.getLayoutPosition();
                    if(itemClickHandler != null){
                        itemClickHandler.itemClick(pos,vh);
                    }
                }
            });
        }
    }
}
