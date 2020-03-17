package com.example.myapplication.ui.cart;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.interfaces.cart.CartConstart;
import com.example.myapplication.models.bean.RelatedBean;
import com.example.myapplication.persenter.cart.CartPersenter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodInfoActivity extends BaseActivity<CartConstart.Persenter> implements CartConstart.View {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_des)
    TextView txtDes;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_nums)
    TextView txtNums;
    @BindView(R.id.layout_nums)
    ConstraintLayout layoutNums;
    @BindView(R.id.txt_param)
    TextView txtParam;
    @BindView(R.id.txt_metarial)
    TextView txtMetarial;
    @BindView(R.id.layout_metarial)
    ConstraintLayout layoutMetarial;
    @BindView(R.id.txt_size)
    TextView txtSize;
    @BindView(R.id.layout_size)
    ConstraintLayout layoutSize;
    @BindView(R.id.txt_color)
    TextView txtColor;
    @BindView(R.id.layout_color)
    ConstraintLayout layoutColor;
    @BindView(R.id.txt_norm)
    TextView txtNorm;
    @BindView(R.id.layout_norm)
    ConstraintLayout layoutNorm;
    @BindView(R.id.txt_place)
    TextView txtPlace;
    @BindView(R.id.myWebView)
    WebView webView;
    @BindView(R.id.txt_question)
    ConstraintLayout txtQuestion;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txt_collect)
    TextView txtCollect;
    @BindView(R.id.txt_cart)
    TextView txtCart;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.txt_addCart)
    TextView txtAddCart;

    @Override
    protected int getLayout() {
        return R.layout.activity_goodinfo;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void initData() {
        int relatedId = getIntent().getIntExtra("relatedId", 0);
        persenter.getRelatedData(relatedId);
    }

    @Override
    protected CartConstart.Persenter createPersenter() {
        return new CartPersenter();
    }

    @Override
    public void getRelatedDataReturn(RelatedBean result) {
        updateBanner(result.getData().getGallery());

        String price = getResources().getString(R.string.price_news_model).replace("$",String.valueOf(result.getData().getInfo().getRetail_price()));
        updatePrice(result.getData().getInfo().getName(),
                result.getData().getInfo().getGoods_brief(),price);

        updateWebView(result.getData().getInfo());
    }

    private void updateBanner(List<RelatedBean.DataBeanX.GalleryBean> list){

    }

    private void updatePrice(String name,String des,String price){
        txtTitle.setText(name);
        txtDes.setText(des);
        txtPrice.setText(price);
    }

    private void updateParam(){
    }

    private void updateWebView(RelatedBean.DataBeanX.InfoBean infoBean){

        String css_str = getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>"+css_str+"</style></head><body>");
        sb.append(infoBean.getGoods_desc()+"</body></html>");
        webView.loadData(sb.toString(),"text/html","utf-8");

        List<String> urlList = new ArrayList<>();
        String[] arr = infoBean.getGoods_desc().split("<p>");
        String head = "<img src=\"";
        String foot = ".jpg";
        for(int i=0; i<arr.length; i++){
            if(TextUtils.isEmpty(arr[i])) continue;
            int start = arr[i].indexOf(head)+head.length();
            if(start == -1) continue;
            int end = arr[i].indexOf(foot)+foot.length();
            String url = arr[i].substring(start,end);
            urlList.add(url);
            Log.i("url",url);
        }
    }

    private void updateGoodList(){

    }


}
