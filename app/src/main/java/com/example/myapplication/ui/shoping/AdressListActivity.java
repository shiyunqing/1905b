package com.example.myapplication.ui.shoping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.interfaces.IBasePersenter;
import com.example.myapplication.interfaces.cart.ShoppingConstact;
import com.example.myapplication.models.bean.AddressBean;
import com.example.myapplication.persenter.cart.AdressPresenter;
import com.example.myapplication.ui.shoping.adapters.AdressAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdressListActivity extends BaseActivity<ShoppingConstact.AdressPresenter> implements ShoppingConstact.AdressView,
        BaseAdapter.ItemClickHandler {
    @BindView(R.id.recy_adress_list)
    RecyclerView recyAdressList;
    @BindView(R.id.txt_new)
    TextView txtNew;

    AdressAdapter adressAdapter;
    List<AddressBean.DataBean> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_adress_list;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adressAdapter = new AdressAdapter(list,this);
        recyAdressList.setLayoutManager(new LinearLayoutManager(this));
        recyAdressList.setAdapter(adressAdapter);

        adressAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.img_editor:
                        AddressBean.DataBean item = (AddressBean.DataBean) v.getTag();
                        Intent intent = new Intent(AdressListActivity.this, AdressEditorActivity.class);
                        intent.putExtra("adress",item);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        persenter.getAdressList();
    }

    @Override
    protected ShoppingConstact.AdressPresenter createPersenter() {
        return new AdressPresenter();
    }

    @OnClick(R.id.txt_new)
    public void onViewClicked() {
        Intent intent = new Intent(this,AdressEditorActivity.class);
        startActivity(intent);
    }

    @Override
    public void getAdressListReturn(AddressBean result) {
        adressAdapter.updata(result.getData());
    }

    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
        AddressBean.DataBean item = list.get(position);
        Intent intent = new Intent(this,AdressEditorActivity.class);
        intent.putExtra("adress",item);
        startActivity(intent);
    }
}
