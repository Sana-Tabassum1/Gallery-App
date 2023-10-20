package com.example.retrofit.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit.api.Service
import com.example.retrofit.db.quoteDatabase
import com.example.retrofit.model.Favorites
import com.example.retrofit.model.Hit
import com.example.retrofit.model.ListApi
import com.example.retrofit.utils.networkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class retroRepository(
    private val service: Service,
    private val quoteDatabase: quoteDatabase,
    private val applicationContext: Context
) {
    private val picLiveData = MutableLiveData<ListApi>()

    // Change favoriteImageIdsLiveData to MutableLiveData
    val favoriteImageIdsLiveData: MutableLiveData<List<Int>> =
        MutableLiveData()

    //public acces data
    val picRetro: LiveData<ListApi>
        get() = picLiveData

    val images: LiveData<ListApi>
        get() = picLiveData


    suspend fun favorites(fa: Favorites) {
        quoteDatabase.quoteDAO().insertfav(fa)
    }

    fun getfavorites(): LiveData<List<Favorites>> {
        return quoteDatabase.quoteDAO().getfav()
    }

    suspend fun delfavorites(item: Favorites) {
        quoteDatabase.quoteDAO().delfav(item.favoriteId)
    }


    suspend fun getImages(page: Int, category: String) {
        if (networkUtils.isInternetAvailable(applicationContext)) {
            Log.d("MYCODELOG", "internet avaialble")

            val result = service.getservice(category, page)

            if (result.body() != null) {
                Log.d("MYCODELOG", "Api Called")

                quoteDatabase.quoteDAO().addImages(result.body()!!.hits)
                picLiveData.postValue(result.body())
            }
        } else {
            Log.d("MYCODELOG", "Database called")

            val images = quoteDatabase.quoteDAO().getImages()
            val imageList = ListApi(images, 1, 1)
            picLiveData.postValue(imageList)
        }
    }

    // Function to remove a favorite by its imageId
    suspend fun removeFavorite(favorite: Favorites) {
        withContext(Dispatchers.IO) {
            quoteDatabase.quoteDAO().removeFavorite(favorite)
        }
    }

    suspend fun toggleFavorite(image: Hit) {
        withContext(Dispatchers.IO) {
            val isFavorite = quoteDatabase.quoteDAO().isFavorite(image.id)
//            Log.d("MyME", isFavorite.toString())

            if (isFavorite) {
                // Image is a favorite, remove it
                quoteDatabase.quoteDAO().removeFavoriteByImageId(image.id)
            } else {
                // Image is not a favorite, add it
                val favoriteEntity = Favorites(
                    0,  // You can use 0 here as the auto-generated ID
                    imageId = image.imageId,
                    largeImageURL = image.largeImageURL,
                    id = image.id,
                    likes = image.likes,
                    views = image.views,
                    tags = image.tags,
                    type = image.type,
                    downloads = image.downloads,
                    comments = image.comments,
                    user = image.user,
                )
                quoteDatabase.quoteDAO().addFavorite(favoriteEntity)
            }


        }
    }

    // Return LiveData here
    fun getFavorites(): LiveData<List<Favorites>> {
        return quoteDatabase.quoteDAO().getFavoritesLiveData()
    }

    // Suspend function to check if an image is a favorite
    suspend fun isFavorite(imageId: Int): Boolean {
        return quoteDatabase.quoteDAO().isFavorite(imageId)
    }

    suspend fun fetchFavoriteImageIds() {
        val favoriteImageIds = quoteDatabase.quoteDAO().getFavoriteImageIds()
        favoriteImageIdsLiveData.value = favoriteImageIds
    }

    suspend fun getQuote(): List<Hit> {
        return quoteDatabase.quoteDAO().getQuote()
    }
}