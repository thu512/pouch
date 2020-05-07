package com.jinjoo.pouch.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jinjoo.pouch.R;
import com.jinjoo.pouch.adapter.SearchAdapter;
import com.jinjoo.pouch.databinding.ActivitySearchBinding;
import com.jinjoo.pouch.model.pub.Item;
import com.jinjoo.pouch.model.pub.Res;
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

                    //getImg(response.body().getBody().getItems().get(0).getITEM_NAME());
                    if(response.body().getBody().getTotalCount().equals("0")){
                        //검색 결과 없을때
                    }else{
                        setRecyclerView(response.body().getBody().getItems());
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




    public void setRecyclerView(List<Item> list){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());       //리사이클러뷰 내부 레이아웃 형식
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);                                 //방향 설정
        binding.searchList.setLayoutManager(layoutManager);                                       //set
        adapter = new SearchAdapter(getApplicationContext(), list);                                       //어댑터에 데이터 셋팅
        binding.searchList.setAdapter(adapter);
    }
}
