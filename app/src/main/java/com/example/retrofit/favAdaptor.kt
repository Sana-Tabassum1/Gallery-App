package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.FavlayoutBinding
import com.example.retrofit.databinding.ImagelayoutBinding
import com.example.retrofit.model.Favorites
import com.example.retrofit.model.Hit
import com.squareup.picasso.Picasso

class favAdaptor(private val favList:ArrayList<Favorites>):RecyclerView.Adapter<favAdaptor.MyHolder>() {
    private var delBtnListeber:favAdaptor.DelButtonClickListener?=null

    interface DelButtonClickListener{
        fun onDelButtonClick(item:Favorites)
    }
    fun setOnDelClickListener(listener: DelButtonClickListener){
        delBtnListeber=listener
    }
    inner class MyHolder(private val binding:FavlayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Favorites){
            Picasso.get().load(item.webformatURL).into(binding.imageView)

            binding.deletebtn.setOnClickListener {
                delBtnListeber?.onDelButtonClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favAdaptor.MyHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding=FavlayoutBinding.inflate(inflater,parent,false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: favAdaptor.MyHolder, position: Int) {
        val currentItem=favList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
       return favList.size
    }
}