package com.jinjoo.pouch.net;

import com.jinjoo.pouch.model.pub.Res;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface AllFactoryIm {

    //공공데이터 화장품 이름 가져오기
    @GET("getReportItemList")
    Call<Res> cos(@Query("item_name") String itemName);


    //네이버 화장품 이미지 검색
    @GET("shop.json")
    Call<com.jinjoo.pouch.model.naver.Res> img(@Header("X-Naver-Client-Id") String id, @Header("X-Naver-Client-Secret") String code, @Query("query") String query, @Query("display") int n);
}
