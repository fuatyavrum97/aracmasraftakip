package com.fuat.aracmasraftakip.database.repository

import com.fuat.aracmasraftakip.database.AppDao
import com.fuat.aracmasraftakip.database.entity.CategoryEntity

class CategoryRepository(private val appDao: AppDao) {

    suspend fun getAllCategories(): List<CategoryEntity> {
        return appDao.getAllCategories()
    }

    suspend fun insertCategory(category: CategoryEntity) {
        appDao.insertCategory(category)
    }

    suspend fun getCategoryById(categoryId: Int): CategoryEntity? {
        return appDao.getCategoryById(categoryId)
    }
}

