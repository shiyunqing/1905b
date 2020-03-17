package com.example.myapplication.ui.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.common.Constant;
import com.example.myapplication.models.bean.IndexBean;

import java.util.List;

public class BrandAdapter extends BaseAdapter {
    public BrandAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_brand_item;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        IndexBean.DataBean.BrandListBean data = (IndexBean.DataBean.BrandListBean) o;
        ImageView img_brand = (ImageView) holder.getView(R.id.img_brand);
        TextView txt_name = (TextView)holder.getView(R.id.txt_name);
        TextView txt_price = (TextView)holder.getView(R.id.txt_price);
        Glide.with(mContext).load(data.getNew_pic_url()).into(img_brand);
        txt_name.setText(data.getName());
        txt_price.setText(Constant.PRICE_MODEL.replace("$",data.getFloor_price()));
    }
}
