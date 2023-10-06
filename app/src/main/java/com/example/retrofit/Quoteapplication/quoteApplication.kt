package com.example.retrofit.Quoteapplication

import android.app.Application
import com.example.retrofit.api.Service
import com.example.retrofit.api.retrofithelper
import com.example.retrofit.db.quoteDatabase
import com.example.retrofit.repository.retroRepository

class quoteApplication:Application() {

    lateinit var retroRepository: retroRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }
    private fun initialize(){
        val service =retrofithelper.getInstance().create(Service::class.java)
        val database= quoteDatabase.getquoteDatabase(applicationContext)
        retroRepository =retroRepository(service,database,applicationContext)

    }
}