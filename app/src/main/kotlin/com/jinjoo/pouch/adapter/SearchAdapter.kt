package com.jinjoo.pouch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jinjoo.pouch.GlideApp
import com.jinjoo.pouch.R
import com.jinjoo.pouch.api.model.Cosmetic
import kotlinx.android.synthetic.main.item_search.view.*

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var items: MutableList<Cosmetic> = mutableListOf()
    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    fun setItemClickListener(listener: ItemClickListener?) {
        this.listener = listener
    }

    fun setItems(items: MutableList<Cosmetic>) {
        this.items = items
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let { item ->
            with(holder.itemView) {
                GlideApp.with(context).load(items[position].img).into(img)
                brand.text = item.brand
                name.text = item.name
                regi.setOnClickListener { listener?.onItemClick(item) }
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    //뷰홀더
    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_search, parent, false)
    )

    interface ItemClickListener {

        fun onItemClick(cos: Cosmetic)
    }
}