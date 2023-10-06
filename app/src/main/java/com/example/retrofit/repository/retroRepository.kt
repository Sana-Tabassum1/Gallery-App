package com.example.retrofit.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit.api.Service
import com.example.retrofit.db.quoteDatabase
import com.example.retrofit.model.Favorites
import com.example.retrofit.model.Hit
import com.example.retrofit.model.ListApi
import com.example.retrofit.utils.networkUtils

class retroRepository(
    private val service: Service,
    private val quoteDatabase: quoteDatabase,
    private val applicationContext: Context
) {
    private val picLiveData = MutableLiveData<ListApi>()

    //public acces data
    val picRetro:LiveData<ListApi>
        get()=picLiveData


     suspend fun favorites(fa:Favorites){
         quoteDatabase.quoteDAO().insertfav(fa)
     }
     fun getfavorites():LiveData<List<Favorites>>{
       return quoteDatabase.quoteDAO().getfav()
    }

    suspend fun delfavorites(item: Favorites){
        quoteDatabase.quoteDAO().delfav(item.favId)
    }

    suspend fun getService(page:Int){
        if (networkUtils.isInternetAvailable(applicationContext)){
            val result =service.getservice(1 )
            if (result?.body()!=null){
                quoteDatabase.quoteDAO().insertQuote(result.body()!!.hits)
                picLiveData.postValue(result.body())
            }
        }
        else
        {
            val quote=quoteDatabase.quoteDAO().getQuote()
            val quotelist=ListApi(hits = quote,1,1)
            picLiveData.postValue(quotelist)

        }




    }
}