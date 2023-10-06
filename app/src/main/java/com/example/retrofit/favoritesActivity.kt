package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.Quoteapplication.quoteApplication
import com.example.retrofit.databinding.ActivityFavoritesBinding
import com.example.retrofit.model.Favorites
import com.example.retrofit.viewModel.mainViewmodelfactory
import com.example.retrofit.viewModel.mainviewModel

class favoritesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityFavoritesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_favorites)

        val repository= (application as quoteApplication).retroRepository
        val mainviewModel: mainviewModel = ViewModelProvider(this,
            mainViewmodelfactory(repository)
        ).get(mainviewModel::class.java)


        recyclerView=binding.FavoritesRecycler
        recyclerView.layoutManager= LinearLayoutManager(this)

        mainviewModel.getfav().observe(this, Observer {it->
            val adaptor=favAdaptor(it as ArrayList<Favorites>)
            recyclerView.adapter=adaptor


            adaptor.setOnDelClickListener(object: favAdaptor.DelButtonClickListener{

                override fun onDelButtonClick(item: Favorites) {
                    mainviewModel.delfav(item)

                }

            })
        })


//        holder.DeleteInfo.setOnClickListener {
//
//            deleteBtnListener?.onDeleteButtonClick(detaillist[position])
//            //  detaillist.removeAt(position)
//            notifyItemRemoved(position)
//            val propertyEntity = detaillist[position]
//            showDeleteConfirmationDialog(propertyEntity)
//            //onDeleteClickListener(propertyEntity) // Call the delete click listener callback
//        }
    }

//    private fun showDeleteConfirmationDialog(propertyEntity: propertyEntitiy) {
//        AlertDialog.Builder(context)
//            .setTitle("Delete Item")
//            .setMessage("Are you sure you want to delete this item?")
//            .setPositiveButton("Yes") { _, _ ->
//                // Handle the deletion here
//                // You can delete the item from the database and notify the adapter
//                // Database.propertyDAO().deleteItem(propertyEntity)
//                // detaillist.remove(propertyEntity)
//                // notifyDataSetChanged()
//            }
//            .setNegativeButton("No", null)
//            .show()
//    }
}