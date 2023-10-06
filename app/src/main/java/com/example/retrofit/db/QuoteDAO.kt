package com.example.retrofit.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.retrofit.model.Favorites
import com.example.retrofit.model.Hit


@Dao
interface QuoteDAO {

    @Insert
    suspend fun insertQuote(quote:List<Hit>)

    @Query("SELECT * FROM QUOTE")
    suspend fun getQuote(): List<Hit>


    //fav
    @Insert
    suspend fun insertfav(favorites: Favorites)

    @Query("SELECT * FROM favorites")
    fun getfav(): LiveData<List<Favorites>>

    @Query("DELETE FROM favorites WHERE favId=:favId")
    suspend fun delfav(favId:Int)
}