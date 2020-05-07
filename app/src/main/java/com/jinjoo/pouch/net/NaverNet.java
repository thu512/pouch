package com.jinjoo.pouch.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NaverNet {
    private static final NaverNet ourInstance = new NaverNet();
    public static NaverNet getInstance() {
        return ourInstance;
    }
    private NaverNet() {}


    //retrofit 생성

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/v1/search/") //기본도메인 설정
            .addConverterFactory(GsonConverterFactory.create())  //응답데이터를 json 자동 변환
            .build();

    public Retrofit getRetrofit() {
        return retrofit;
    }

    //API담당 인터페이스 생성
    //API담당 인터페이스의 객체를 생성
    AllFactoryIm allFactoryIm;


    //객체를 리턴해주는 getter 준비
    public AllFactoryIm getAllFactoryIm() {
        if(allFactoryIm == null){
            allFactoryIm =retrofit.create(AllFactoryIm.class);
        }
        return allFactoryIm;
    }

}
