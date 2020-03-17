package com.example.myapplication.ui.home.activity;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.models.bean.BrandGoodsBean;

import java.util.List;

public class BrandGoodsAdapter extends BaseAdapter {
    public BrandGoodsAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_news_item;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        BrandGoodsBean.DataBeanX.GoodsListBean bean = (BrandGoodsBean.DataBeanX.GoodsListBean) o;
        ImageView img = (ImageView)holder.getView(R.id.img_news);
        TextView txt_name = (TextView)holder.getView(R.id.txt_name);
        TextView txt_price = (TextView)holder.getView(R.id.txt_price);
        Glide.with(mContext).load(bean.getList_pic_url()).into(img);
        txt_name.setText(bean.getName());
        String price = mContext.getResources().getString(R.string.price_news_model);
        txt_price.setText(price.replace("$",String.valueOf(bean.getRetail_price())));
    }
}
