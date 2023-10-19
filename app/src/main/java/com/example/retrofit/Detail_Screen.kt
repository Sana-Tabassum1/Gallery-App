package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.retrofit.databinding.ActivityDetailScreenBinding
import com.example.retrofit.databinding.ActivityFavoritesBinding
import com.example.retrofit.model.Hitwithfav

class Detail_Screen : AppCompatActivity() {
    private lateinit var binding: ActivityDetailScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_detail_screen)

        val hit=intent.getSerializableExtra("Hit") as Hitwithfav
//        hit.hit.likes.toString()
//        binding.tv.text=
        binding.downloadsTextView.text="Downloads ${hit.hit.downloads}"
        binding.tagsTextView.text="${hit.hit.tags}"
        binding.typesTextView.text="${hit.hit.type}"
        binding.userTextView.text="${hit.hit.user}"

    }
}