package com.jinjoo.pouch.net;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class PublicNet {
    private static final PublicNet ourInstance = new PublicNet();
    public static PublicNet getInstance() {
        return ourInstance;
    }
    private PublicNet() {}



    //retrofit 생성

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://opendata.mfds.go.kr/portal/FcssReportPrdlstInforService/") //기본도메인 설정
            .addConverterFactory(SimpleXmlConverterFactory.create())  //응답데이터를 json 자동 변환
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
