package com.jinjoo.pouch.api


import com.jinjoo.pouch.api.model.pub.Res
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface NameApi{
    //공공데이터 화장품 이름 가져오기
    @GET("getReportItemList")
    fun cos(
            @Query("item_name") itemName: String,
            @Query("numOfRows") numOfRows: Int
            ): Observable<Res>
}