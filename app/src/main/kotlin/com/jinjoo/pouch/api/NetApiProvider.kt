package com.jinjoo.pouch.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

//retrofit 생성
fun provideSearchCosApi(): NameApi = Retrofit.Builder()
        .baseUrl("http://opendata.mfds.go.kr/portal/FcssReportPrdlstInforService/") //기본도메인 설정
        .addConverterFactory(SimpleXmlConverterFactory.create()) //응답데이터를 json 자동 변환
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .build()
        .create(NameApi::class.java)

//retrofit 생성
fun provideImgApi():ImgApi = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com/v1/search/") //기본도메인 설정
        .addConverterFactory(GsonConverterFactory.create()) //응답데이터를 json 자동 변환
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .build()
        .create(ImgApi::class.java)