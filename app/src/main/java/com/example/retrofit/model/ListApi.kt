package com.example.retrofit.model

data class ListApi(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)