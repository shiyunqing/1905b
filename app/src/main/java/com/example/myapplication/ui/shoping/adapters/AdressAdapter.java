package com.example.myapplication.ui.shoping.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.models.bean.AddressBean;

import java.util.List;

public class AdressAdapter extends BaseAdapter {
    public AdressAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_adresslist_item;
    }

    @Override
    public void bindData(BaseAdapter.BaseViewHolder holder, Object o) {
        AddressBean.DataBean bean = (AddressBean.DataBean) o;
        TextView txtName = (TextView) holder.getView(R.id.txt_name);
        TextView txtDefault = (TextView) holder.getView(R.id.txt_default);
        TextView txtPhone = (TextView) holder.getView(R.id.txt_phone);
        TextView txtAdress = (TextView) holder.getView(R.id.txt_adress);
        ImageView imgEditor = (ImageView) holder.getView(R.id.img_editor);
        txtName.setText(bean.getName());
        txtDefault.setVisibility(bean.getIs_default() == 1 ? View.VISIBLE : View.GONE);
        txtPhone.setText(bean.getMobile());
        txtAdress.setText(bean.getCity_name()+bean.getDistrict_name()+bean.getFull_region());
        imgEditor.setTag(bean);
        if(clickListener != null){
            imgEditor.setOnClickListener(clickListener);
        }
    }
}
