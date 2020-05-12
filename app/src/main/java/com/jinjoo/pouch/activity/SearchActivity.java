package com.jinjoo.pouch.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.zxing.integration.android.IntentIntegrator;
import com.jinjoo.pouch.R;
import com.jinjoo.pouch.adapter.SearchAdapter;
import com.jinjoo.pouch.bacode.CustomScannerActivity;
import com.jinjoo.pouch.databinding.ActivitySearchBinding;
import com.jinjoo.pouch.model.Cosmetic;
import com.jinjoo.pouch.model.pub.Item;
import com.jinjoo.pouch.model.pub.Res;
import com.jinjoo.pouch.net.NaverNet;
import com.jinjoo.pouch.net.PublicNet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends Activity {

    ActivitySearchBinding binding;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);



        //직접입력 버튼
        binding.direct.setOnClickListener((view -> {

        }));

        //바코드 버튼
        binding.bacode.setOnClickListener((view -> {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setBeepEnabled(false);
            integrator.setCaptureActivity(CustomScannerActivity.class);
            integrator.initiateScan();

        }));

        //검색 버튼
        binding.search.setOnClickListener((view -> {
            getCosmetic(binding.name.getText().toString());
        }));
    }


    public void getCosmetic(String itemName){
        Call<Res> res = PublicNet.getInstance().getAllFactoryIm().cos(itemName);
        res.enqueue(new Callback<Res>() {
            @Override
            public void onResponse(Call<Res> call, Response<Res> response) {

                if(response.isSuccessful()){
                    List<Item> items = response.body().getBody().getItems();

                    //getImg(response.body().getBody().getItems().get(0).getITEM_NAME());
                    if(response.body().getBody().getTotalCount().equals("0")){
                        //검색 결과 없을때
                    }else{

                        //getImg();

                    }


                }else {
                    Log.d("jinjoo", "실패");
                }
            }

            @Override
            public void onFailure(Call<Res> call, Throwable t) {
                Log.d("jinjoo", "실패"+t.getLocalizedMessage());
            }
        });
    }

    public void getImg(String name, Cosmetic cos){
        Call<com.jinjoo.pouch.model.naver.Res> res = NaverNet.getInstance().getAllFactoryIm().img(getResources().getString(R.string.naver_id), getResources().getString(R.string.naver_key), name, 10);
        res.enqueue(new Callback<com.jinjoo.pouch.model.naver.Res>() {
            @Override
            public void onResponse(Call<com.jinjoo.pouch.model.naver.Res> call, Response<com.jinjoo.pouch.model.naver.Res> response) {
                if(response.isSuccessful()){
                    Log.d("jinjoof", "사진은: "+response.body().getItems().toString());

                    //setRecyclerView(response.body().getBody().getItems());

                    for(int i=0; i<response.body().getItems().size(); i++){
                        if(response.body().getItems().get(i).getImage()!=null){
                            cos.setImg(response.body().getItems().get(i).getImage());
                            Log.d("jinjoof", "사진: "+cos.getImg());
                            break;
                        }
                    }


                }else{

                }
            }

            @Override
            public void onFailure(Call<com.jinjoo.pouch.model.naver.Res> call, Throwable t) {
                Log.d("jinjoo", "실패"+t.getLocalizedMessage());
            }
        });
    }


    public void setRecyclerView(List<Cosmetic> list){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());       //리사이클러뷰 내부 레이아웃 형식
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);                                 //방향 설정
        binding.searchList.setLayoutManager(layoutManager);                                       //set
        adapter = new SearchAdapter(getApplicationContext(), list);                                       //어댑터에 데이터 셋팅
        binding.searchList.setAdapter(adapter);
    }
}
