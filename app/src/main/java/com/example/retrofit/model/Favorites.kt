package com.example.retrofit.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
data class Favorites(
    @PrimaryKey(autoGenerate = true)
    val favId:Int,
    val quoteId:Int,
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val largeImageURL: String,
    val likes: Int,
    val views: Int,
    val webformatURL: String,


)
