package com.example.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ImagelayoutBinding
import com.example.retrofit.model.Hit
import com.squareup.picasso.Picasso

class imagrAdaptor(private val context: Context,private val imageList:ArrayList<Hit>):RecyclerView.Adapter<imagrAdaptor.MyHolder>() {


    private var favBtnListeber:imagrAdaptor.FavButtonClickListener?=null

    interface FavButtonClickListener{
        fun onFavButtonClick(item:Hit)
    }
    fun setOnFavClickListener(listener: FavButtonClickListener){
        favBtnListeber=listener
    }

    inner class MyHolder(private val binding:ImagelayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Hit){
            Picasso.get().load(item.webformatURL).into(binding.imageView)

            binding.favouriteBtn.setOnClickListener {
                favBtnListeber?.onFavButtonClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): imagrAdaptor.MyHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=ImagelayoutBinding.inflate(inflater,parent,false)
        return MyHolder(binding)

    }

    override fun onBindViewHolder(holder: imagrAdaptor.MyHolder, position: Int) {
        val currentItem=imageList[position]
        holder.bind(currentItem)


    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}