package com.example.retrofit.model

import java.io.Serializable

data class Hitwithfav(
    val hit: Hit,
    var isFavorite: Boolean = false
):Serializable

