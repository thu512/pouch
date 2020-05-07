package com.jinjoo.pouch.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jinjoo.pouch.R;
import com.jinjoo.pouch.model.Cosmetic;
import com.jinjoo.pouch.model.naver.Res;
import com.jinjoo.pouch.model.pub.Item;
import com.jinjoo.pouch.net.NaverNet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;
    List<Item> items;
    List<Cosmetic> list;


    //어댑터 생성자 -> getApplicationContext, 리스트로 보여줄 아이템
    public SearchAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
        list = new ArrayList<>();
        for (int i=0; i<items.size(); i++){
            list.add(new Cosmetic(items.get(i).getITEM_NAME(), items.get(i).getENTP_NAME()));

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_search, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        getImg(items.get(position).getITEM_NAME(),list.get(position));
        Log.d("jinjoof", "사진3: "+list.get(position).getImg());
        Glide.with(context).load(list.get(position).getImg()).into(holder.imageView);
        holder.brand.setText(list.get(position).getBrand());
        holder.name.setText(list.get(position).getName());

        holder.regi.setOnClickListener(view -> {
            //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imgData.getLink()));
            //startActivity(context,intent,null);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void getImg(String name, Cosmetic cos){
        Call<Res> res = NaverNet.getInstance().getAllFactoryIm().img(context.getResources().getString(R.string.naver_id), context.getResources().getString(R.string.naver_key), name, 10);
        res.enqueue(new Callback<Res>() {
            @Override
            public void onResponse(Call<com.jinjoo.pouch.model.naver.Res> call, Response<Res> response) {
                if(response.isSuccessful()){
                    Log.d("jinjoof", "사진은: "+response.body().getItems().toString());
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


    //뷰홀더
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView brand;
        TextView name;
        Button regi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            brand = itemView.findViewById(R.id.brand);
            name = itemView.findViewById(R.id.name);
            regi = itemView.findViewById(R.id.regi);
        }
    }
}
