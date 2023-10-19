package com.example.retrofit.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofit.model.Favorites
import com.example.retrofit.model.Hit

@Database(entities = [Hit::class,Favorites::class], version = 7)
abstract class quoteDatabase: RoomDatabase(){

    abstract fun quoteDAO():QuoteDAO

    companion object {
        @Volatile
        private var INSTANCE: quoteDatabase? = null

        fun getquoteDatabase(context: Context): quoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        quoteDatabase::class.java,
                        "quoteDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }


    }

}