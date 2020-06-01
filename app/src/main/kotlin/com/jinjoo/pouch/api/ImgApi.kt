package com.jinjoo.pouch.api

import com.jinjoo.pouch.api.model.naver.Res
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImgApi{
    //네이버 화장품 이미지 검색
    @GET("shop.json")
    fun img(@Header("X-Naver-Client-Id") id: String
            , @Header("X-Naver-Client-Secret") code: String
            , @Query("query") query: String
            , @Query("display") n: Int): Observable<Res>
}