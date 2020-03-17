package com.example.myapplication.models.api;

import com.example.myapplication.models.bean.AddressBean;
import com.example.myapplication.models.bean.AdressSaveBean;
import com.example.myapplication.models.bean.BrandBean;
import com.example.myapplication.models.bean.BrandGoodsBean;
import com.example.myapplication.models.bean.CartBean;
import com.example.myapplication.models.bean.CartGoodsCheckBean;
import com.example.myapplication.models.bean.CartGoodsDeleteBean;
import com.example.myapplication.models.bean.CartGoodsUpdateBean;
import com.example.myapplication.models.bean.IndexBean;
import com.example.myapplication.models.bean.OrderInfoBean;
import com.example.myapplication.models.bean.RegionBean;
import com.example.myapplication.models.bean.RelatedBean;
import com.example.myapplication.models.bean.SortBean;
import com.example.myapplication.models.bean.SortDetailGoodsBean;
import com.example.myapplication.models.bean.SortDetailTabBean;
import com.example.myapplication.models.bean.SortGoodsBean;
import com.example.myapplication.models.bean.UserBean;
import com.example.myapplication.models.bean.VerifyBean;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ShopApi {

    @GET("index")
    Flowable<IndexBean> getIndexData();

    @GET("brand/detail")
    Flowable<BrandBean> getBrandInfo(@Query("id") String id);

    @GET("goods/list")
    Flowable<BrandGoodsBean> getBrandGoods(@Query("brandId") String brandId, @Query("page") int page, @Query("size") int size);

    @GET("catalog/index")
    Flowable<SortBean> getSort();

    @GET("catalog/current")
    Flowable<SortGoodsBean> getCurrentSortData(@Query("id") int id);

    @GET("goods/category")
    Flowable<SortDetailTabBean> getSortDetailTab(@Query("id") int id);

    @GET("goods/list")
    Flowable<SortDetailGoodsBean> getSortDetailGoods(@Query("categoryId") int id, @Query("page") int page, @Query("size") int size);

    @GET("goods/detail")
    Flowable<RelatedBean> getRelatedData(@Query("id") int id);

    @GET("auth/verify")
    Flowable<VerifyBean> getVerify();

    @POST("auth/login")
    @FormUrlEncoded
    Flowable<UserBean> login(@Field("nickname") String nickname, @Field("password") String password);

    @GET("cart/index")
    Flowable<CartBean> getCartIndex();

    @POST("cart/checked")
    @FormUrlEncoded
    Flowable<CartGoodsCheckBean> setCartGoodsCheck(@Field("productIds") String pids, @Field("isChecked") int isChecked);

    @POST("cart/update")
    @FormUrlEncoded
    Flowable<CartGoodsUpdateBean> updateCartGoods(@Field("productId") String pids, @Field("goodsId") String goodsId, @Field("number") int number, @Field("id") int id);

    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<CartGoodsDeleteBean> deleteCartGoods(@Field("productIds") String pids);

    @GET("address/list")
    Flowable<AddressBean> getAddress();

    @POST("address/save")
    @FormUrlEncoded
    Flowable<AdressSaveBean> saveAdress(@FieldMap Map adressMap);

    @GET("region/list")
    Flowable<RegionBean> getRegion(@Query("parentId") int parentId);


    @GET("cart/checkout")
    Flowable<OrderInfoBean> getOrderInfo(@Query("addressId") int addressId, @Query("couponId") int couponId);


    @POST("auth/applogin")
    Call<ResponseBody> applogin(@Body RequestBody body);

    @Multipart
    @POST("file_upload.php")
    Call<ResponseBody> uploadImg(@Part("key") RequestBody param, @Part MultipartBody.Part part);

}
