package com.example.retrofit.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.model.Favorites
import com.example.retrofit.model.Hit
import com.example.retrofit.model.ListApi
import com.example.retrofit.repository.retroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class mainviewModel(private val Repository: retroRepository):ViewModel() {
      init {
          viewModelScope.launch(Dispatchers.IO) {

              Repository.getService(1)
          }

      }
    fun getfav():LiveData<List<Favorites>>{
        return Repository.getfavorites()
    }

    fun delfav(item: Favorites){
        viewModelScope.launch(Dispatchers.IO) {
            Repository.delfavorites(item)
        }

    }
    fun insert(hit: Hit){
        val fav = Favorites(
            0,
            hit.quoteId,
            hit.comments,
            hit.downloads,
            hit.id,
            hit.largeImageURL,
            hit.likes,
            hit.views,
            hit.webformatURL
        )
        viewModelScope.launch(Dispatchers.IO) {
            Repository.favorites(fav)

        }
    }
    val picRetro:LiveData<ListApi>
        get() = Repository.picRetro
}