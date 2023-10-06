package com.example.retrofit.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.repository.retroRepository

class mainViewmodelfactory(private val retroRepository: retroRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mainviewModel(retroRepository) as T
    }
}