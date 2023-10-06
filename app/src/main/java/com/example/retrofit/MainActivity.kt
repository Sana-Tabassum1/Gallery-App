package com.example.retrofit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.Quoteapplication.quoteApplication
import com.example.retrofit.api.Service
import com.example.retrofit.api.retrofithelper
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.db.quoteDatabase
import com.example.retrofit.model.Hit
import com.example.retrofit.repository.retroRepository
import com.example.retrofit.viewModel.mainViewmodelfactory
import com.example.retrofit.viewModel.mainviewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding:ActivityMainBinding

//    lateinit var mainviewModel: mainviewModel
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

         val repository= (application as quoteApplication).retroRepository

        val mainviewModel:mainviewModel = ViewModelProvider(this,mainViewmodelfactory(repository)).get(mainviewModel::class.java)

    recyclerView=binding.ImageRecycler
    recyclerView.layoutManager=LinearLayoutManager(this)
        mainviewModel.picRetro.observe(this, Observer {
            val adaptor=imagrAdaptor(this,it.hits as ArrayList<Hit>)
            recyclerView.adapter=adaptor

            adaptor.setOnFavClickListener(object: imagrAdaptor.FavButtonClickListener{
                override fun onFavButtonClick(item: Hit) {
                    mainviewModel.insert(item)
                }

            })

           //Log.d("datapppppp",it.hits.toString())
            //Toast.makeText(this,it.hits.toString(),Toast.LENGTH_SHORT).show()
        })

    binding.btnFav.setOnClickListener {
        startActivity(Intent (this@MainActivity,favoritesActivity::class.java))
    }
    }
}