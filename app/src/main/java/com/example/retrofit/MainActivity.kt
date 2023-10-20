package com.example.retrofit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.Quoteapplication.quoteApplication
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.Favorites
import com.example.retrofit.model.Hit
import com.example.retrofit.model.Hitwithfav
import com.example.retrofit.viewModel.mainViewmodelfactory
import com.example.retrofit.viewModel.mainviewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit var loader: ProgressBar


    lateinit var mainViewModel: mainviewModel

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize the loader
        loader = binding.loader


        val repository = (application as quoteApplication).retroRepository

        /*val mainviewModel:mainviewModel*/mainViewModel =
            ViewModelProvider(this, mainViewmodelfactory(repository)).get(mainviewModel::class.java)

        recyclerView = binding.ImageRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = imagrAdaptor(this@MainActivity, emptyList(), { clickedImage ->
            toggleFavorite(clickedImage)
        }, mainViewModel)

        binding.ImageRecycler.adapter = adapter

        showLoader()
        mainViewModel.loadImagesByCategory("background") { // Assume "background" is your default category
            hideLoader()
        }

        // Create an array of category TextViews
        val categoryViews = arrayOf(
            binding.background,
            binding.fashion,
            binding.nature,
            binding.science,
            binding.computer,
            binding.food
        )



        categoryViews.forEach { categoryView ->
            categoryView.setOnClickListener {
                showLoader() // Show loader before loading images
                categoryViews.forEach {
                    it.setTextColor(resources.getColor(R.color.light_grey))
                }
                categoryView.setTextColor(resources.getColor(R.color.black))

                val selectedCategory = categoryView.text.toString()
                mainViewModel.loadImagesByCategory(selectedCategory) { // Assume "background" is your default category
                    hideLoader()
                } // Load images based on the selected category
                //hideLoader()
            }
        }



        mainViewModel.images.observe(this) { imageList ->
            Log.d("MYCODELOG", imageList.toString())
            val hitsWithFavorites = imageList.hits.map { hit: Hit ->
                val isFavorite = mainViewModel.getFavorites().value?.any { favorite: Favorites ->
                    favorite.id == hit.id
                } ?: false // Default to false if the list is null
                Hitwithfav(hit, isFavorite)
            }
            Log.d("MYCODELOG", "favs:$hitsWithFavorites")

            adapter.imageList = hitsWithFavorites

            adapter.notifyDataSetChanged()
        }



        binding.btnFav.setOnClickListener {
            startActivity(Intent(this@MainActivity, favoritesActivity::class.java))
        }

    }

    private fun toggleFavorite(image: Hit) {
        mainViewModel.toggleFavorite(image)
    }

    private fun showLoader() {
        loader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        loader.visibility = View.GONE
    }


}