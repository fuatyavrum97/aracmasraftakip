package com.fuat.aracmasraftakip.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fuat.aracmasraftakip.database.AppDatabase
import com.fuat.aracmasraftakip.database.entity.CategoryEntity
import com.fuat.aracmasraftakip.database.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CategoryRepository

    init {
        val appDao = AppDatabase.getInstance(application).appDao()
        repository = CategoryRepository(appDao)
    }

    fun insertCategory(category: CategoryEntity) = viewModelScope.launch {
        repository.insertCategory(category)
    }

    fun getAllCategories(onResult: (List<CategoryEntity>) -> Unit) = viewModelScope.launch {
        val categories = repository.getAllCategories()
        onResult(categories)
    }

    fun getCategoryById(categoryId: Int, onResult: (CategoryEntity?) -> Unit) = viewModelScope.launch {
        val category = repository.getCategoryById(categoryId)
        onResult(category)
    }
}

