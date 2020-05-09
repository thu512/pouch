package com.jinjoo.pouch.adapter;

import android.content.Context;
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

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;

    List<Cosmetic> items;


    //어댑터 생성자 -> getApplicationContext, 리스트로 보여줄 아이템
    public SearchAdapter(Context context, List<Cosmetic> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_search, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(items.get(position).getImg()).into(holder.imageView);
        holder.brand.setText(items.get(position).getBrand());
        holder.name.setText(items.get(position).getName());

        holder.regi.setOnClickListener(view -> {
            //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imgData.getLink()));
            //startActivity(context,intent,null);
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
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
