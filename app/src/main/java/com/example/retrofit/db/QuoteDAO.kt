package com.example.retrofit.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
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

    @Query("DELETE FROM favorites WHERE favoriteId=:favId")
    suspend fun delfav(favId:Int)

    @Insert
    suspend fun addImages(images: List<Hit>)

    @Query("SELECT * FROM quote")
    suspend fun getImages() : List<Hit>

    @Delete
    suspend fun removeFavorite(favorite: Favorites)


    @Query("SELECT EXISTS (SELECT 1 FROM favorites WHERE id = :id LIMIT 1)")
    suspend fun isFavorite(id: Int): Boolean

    // Function to remove a favorite by its imageId
    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun removeFavoriteByImageId(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorites)

    @Query("SELECT * FROM favorites")
    fun getFavoritesLiveData(): LiveData<List<Favorites>>

    // Add the new function to get favorite image IDs
    @Query("SELECT imageId FROM favorites")
    suspend fun getFavoriteImageIds(): List<Int>

    // Add another function to get favorite image IDs as LiveData
    @Query("SELECT imageId FROM favorites")
    fun getFavoriteImageIdsLiveData(): LiveData<List<Int>>
}