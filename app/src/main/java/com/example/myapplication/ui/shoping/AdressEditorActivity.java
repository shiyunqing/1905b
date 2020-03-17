package com.example.myapplication.ui.shoping;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.interfaces.IBasePersenter;
import com.example.myapplication.interfaces.cart.ShoppingConstact;
import com.example.myapplication.models.bean.AddressBean;
import com.example.myapplication.models.bean.AdressSaveBean;
import com.example.myapplication.models.bean.RegionBean;
import com.example.myapplication.persenter.cart.AdressNewPresenter;
import com.example.myapplication.ui.shoping.adapters.AdressSelectAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdressEditorActivity extends BaseActivity<ShoppingConstact.AdressNewPresenter> implements ShoppingConstact.AdressNewView {
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.txt_adress)
    TextView txtAdress;
    @BindView(R.id.txt_adress_info)
    TextView txtAdressInfo;
    @BindView(R.id.checkbox_default)
    CheckBox checkboxDefault;
    @BindView(R.id.txt_cancel)
    TextView txtCancel;
    @BindView(R.id.txt_save)
    TextView txtSave;

    PopupWindow popupWindow;//
    AddressBean.DataBean addressBean;

    TextView txtProvince,txtCity,txtArea,txtOk;
    RecyclerView recyAdress;
    AdressSelectAdapter adressSelectAdapter;
    List<RegionBean.DataBean> adressList;
    int pid,cid,aid;



    @Override
    protected int getLayout() {
        return R.layout.activity_adress_editor;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if (getIntent().hasExtra("adress")) {
            addressBean = (AddressBean.DataBean) getIntent().getSerializableExtra("adress");
            pid = addressBean.getProvince_id();
            cid = addressBean.getCity_id();
            aid = addressBean.getDistrict_id();
            txtName.setText(addressBean.getName());
            txtPhone.setText(addressBean.getMobile());
            txtAdress.setText(addressBean.getProvince_name()+addressBean.getCity_name()+addressBean.getDistrict_name());
            txtAdressInfo.setText(addressBean.getAddress());
            checkboxDefault.setChecked(addressBean.getIs_default() == 1 ? true : false);
        }
    }

    @Override
    protected ShoppingConstact.AdressNewPresenter createPersenter() {
        return new AdressNewPresenter();
    }

    @OnClick({R.id.txt_cancel, R.id.txt_save,R.id.txt_adress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_cancel:
                finish();
                break;
            case R.id.txt_save:
                saveAdress();
                break;
            case R.id.txt_adress:
                openAdressWindow();
                break;
        }
    }


    private void openAdressWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow_adress,null);
        popupWindow = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,600);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
        txtProvince = view.findViewById(R.id.txt_province);
        txtCity = view.findViewById(R.id.txt_city);
        txtArea = view.findViewById(R.id.txt_area);
        txtOk = view.findViewById(R.id.txt_ok);
        recyAdress = view.findViewById(R.id.recy_adress);
        txtProvince.setOnClickListener(clickListener);
        txtCity.setOnClickListener(clickListener);

        adressList = new ArrayList<>();
        adressSelectAdapter = new AdressSelectAdapter(adressList,this);
        recyAdress.setLayoutManager(new LinearLayoutManager(this));
        recyAdress.setAdapter(adressSelectAdapter);
        //设置条目点击的接口回调
        adressSelectAdapter.setOnItemClickHandler(new BaseAdapter.ItemClickHandler() {
            @Override
            public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
                RegionBean.DataBean bean = adressList.get(position);
                int id = bean.getId();
                resetSelectTxtStyle();  //重置文本样式
                if(bean.getType() == 1){  //选中的省份
                    persenter.getRegion(id);
                    txtProvince.setText(bean.getName());
                    pid = id;
                    cid = 0;
                    aid = 0;
                    txtCity.setTextColor(Color.parseColor("#ff0000"));
                }else if(bean.getType() == 2){  //选中的是城市
                    persenter.getRegion(id);
                    txtArea.setTextColor(Color.parseColor("#ff0000"));
                    txtCity.setText(bean.getName());
                    cid = id;
                    aid = 0;
                }else{ //选中的是区县
                    txtArea.setText(bean.getName());
                    aid = id;
                    adressSelectAdapter.curSelectId = aid;
                    adressSelectAdapter.notifyDataSetChanged();
                }
            }
        });

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pid == 0 || cid == 0 || aid == 0){
                    showTips("请选择省份数据");
                    return;
                }
                txtAdress.setText(txtProvince.getText().toString()+txtCity.getText().toString()+txtArea.getText().toString());
                popupWindow.dismiss();
            }
        });


        if(addressBean != null){
            txtProvince.setText(addressBean.getProvince_name());
            txtCity.setText(addressBean.getCity_name());
            txtArea.setText(addressBean.getDistrict_name());
            txtArea.setTextColor(Color.parseColor("#ff0000"));
            persenter.getRegion(addressBean.getCity_id());
        }else{
            txtProvince.setTextColor(Color.parseColor("#ff0000"));
            persenter.getRegion(1);
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.txt_province:
                    adressSelectAdapter.curSelectId = pid;
                    persenter.getRegion(1);
                    break;
                case R.id.txt_city:
                    adressSelectAdapter.curSelectId = cid;
                    persenter.getRegion(pid);
                    break;
            }
        }
    };

    private void resetSelectTxtStyle(){
        txtProvince.setTextColor(Color.parseColor("#000000"));
        txtCity.setTextColor(Color.parseColor("#000000"));
        txtArea.setTextColor(Color.parseColor("#000000"));
    }

    private void saveAdress(){
        Map<String,String> map = new HashMap<>();
        if(addressBean == null){
            map.put("id","0");
        }else{
            map.put("id",String.valueOf(addressBean.getId()));
        }
        map.put("name",txtName.getText().toString());
        map.put("mobile",txtPhone.getText().toString());
        map.put("province_id",String.valueOf(pid));
        map.put("city_id",String.valueOf(cid));
        map.put("district_id",String.valueOf(aid));
        map.put("address",txtAdressInfo.getText().toString());
        String defalt = checkboxDefault.isChecked() ? "1" : "0";
        map.put("is_default",defalt);
        persenter.saveAdress(map);
    }

    @Override
    public void saveAdressReturn(AdressSaveBean result) {
        finish();
    }

    @Override
    public void getRegionReturn(RegionBean result) {
        if(popupWindow.isShowing()){
            adressSelectAdapter.updata(result.getData());
        }
    }
}
