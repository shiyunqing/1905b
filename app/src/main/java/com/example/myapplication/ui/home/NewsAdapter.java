package com.example.myapplication.ui.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.models.bean.IndexBean;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    public NewsAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_news_item;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        IndexBean.DataBean.NewGoodsListBean bean = (IndexBean.DataBean.NewGoodsListBean) o;
        ImageView img_news = (ImageView)holder.getView(R.id.img_news);
        TextView txt_name = (TextView)holder.getView(R.id.txt_name);
        TextView txt_price = (TextView)holder.getView(R.id.txt_price);
        Glide.with(mContext).load(bean.getList_pic_url()).into(img_news);
        txt_name.setText(bean.getName());
        String price = mContext.getResources().getString(R.string.price_news_model);
        txt_price.setText(price.replace("$",bean.getRetail_price()));
    }
}
