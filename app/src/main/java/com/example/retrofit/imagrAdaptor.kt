package com.example.retrofit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ImagelayoutBinding
import com.example.retrofit.model.Hit
import com.example.retrofit.model.Hitwithfav
import com.example.retrofit.viewModel.mainviewModel
import com.squareup.picasso.Picasso

class imagrAdaptor(val context: Context,var imageList: List</*Hit*/Hitwithfav>, private val onFavoriteClick: (Hit) -> Unit, private val viewModel: mainviewModel,) :
    RecyclerView.Adapter<imagrAdaptor.MyHolder>() {


//    private var favBtnListeber:imagrAdaptor.FavButtonClickListener?=null
//
//    interface FavButtonClickListener{
//        fun onFavButtonClick(item:Hit)
//    }
//    fun setOnFavClickListener(listener: FavButtonClickListener){
//        favBtnListeber=listener
//    }

    inner class MyHolder(private val binding: ImagelayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hitwithfav) {
            Picasso.get().load(item.hit.largeImageURL).into(binding.imageView)
//
//            binding.favouriteBtn.setOnClickListener {
//                favBtnListeber?.onFavButtonClick(item)
//            }


            // Set a click listener for the favorite icon
            binding.favouriteBtn.setOnClickListener {
                // Check if the current image is heart
                if (binding.favouriteBtn.drawable.constantState ==
                    ContextCompat.getDrawable(
                        binding.favouriteBtn.context,
                        R.drawable.favorite_border_icon
                    )?.constantState
                ) {
                    // Change the image to heart_red
                    binding.favouriteBtn.setImageResource(R.drawable.favorite)
                } else {
                    // Change the image to heart
                    binding.favouriteBtn.setImageResource(R.drawable.favorite_border_icon)
                }

                toggleFavorite(item.hit)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): imagrAdaptor.MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImagelayoutBinding.inflate(inflater, parent, false)
        return MyHolder(binding)

    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: imagrAdaptor.MyHolder, position: Int) {
        val currentItem = imageList[position]
        holder.bind(currentItem)

        holder.itemView.setOnClickListener {
            val intent=Intent(context,Detail_Screen::class.java)
            intent.putExtra("Hit",currentItem)
            context.startActivity(intent)
        }

    }

    private fun toggleFavorite(image: Hit) {
        onFavoriteClick(image)
    }
}
