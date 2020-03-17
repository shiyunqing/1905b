package com.example.myapplication.ui.home;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.interfaces.home.HomeConstract;
import com.example.myapplication.models.bean.IndexBean;
import com.example.myapplication.persenter.home.HomePersenter;
import com.example.myapplication.ui.home.activity.BrandActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomeConstract.Persenter> implements HomeConstract.View, BaseAdapter.ItemClickHandler {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerView_News)
    RecyclerView recyclerViewNews;

    BrandAdapter brandAdapter;
    List<IndexBean.DataBean.BrandListBean> list;

    NewsAdapter newsAdapter;
    List<IndexBean.DataBean.NewGoodsListBean> newsList;


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        brandAdapter = new BrandAdapter(list,context);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(brandAdapter);
        brandAdapter.setOnItemClickHandler(this);

        newsList = new ArrayList<>();
        newsAdapter = new NewsAdapter(newsList,context);
        recyclerViewNews.setLayoutManager(new GridLayoutManager(context,2));
        recyclerViewNews.setAdapter(newsAdapter);
        newsAdapter.setOnItemClickHandler(new BaseAdapter.ItemClickHandler() {
            @Override
            public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
                Log.i("newsItemClick",String.valueOf(position));
            }
        });

    }

    @Override
    protected void initData() {
        persenter.getHomeData();
    }

    @Override
    protected HomeConstract.Persenter createPersenter() {
        return new HomePersenter();
    }

    @Override
    public void getHomeDataReturn(IndexBean result) {
        brandAdapter.updata(result.getData().getBrandList());
        newsAdapter.updata(result.getData().getNewGoodsList());
    }

    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
        IndexBean.DataBean.BrandListBean bean = list.get(position);
        ((TextView)holder.getView(R.id.txt_name)).setText(bean.getName()+"新的名字");
        Log.i("brand-click",String.valueOf(position));
        Intent intent = new Intent(getContext(), BrandActivity.class);
        intent.putExtra("brandId",bean.getId());
        startActivity(intent);
    }
}