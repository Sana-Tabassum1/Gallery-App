package com.example.retrofit.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
data class Favorites(

    @PrimaryKey(autoGenerate = true)
    val favoriteId: Int,
    val imageId: Int, // You may want to store the image ID to reference the original image
    val largeImageURL: String,
    val id : Int,
    val likes: Int,
    val views: Int,
    val tags: String,
    val type: String,
    val downloads: Int,
    val comments: Int,
    val user: String,



)
