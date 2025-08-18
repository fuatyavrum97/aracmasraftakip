package com.fuat.aracmasraftakip.database.repository

import com.fuat.aracmasraftakip.database.AppDao
import com.fuat.aracmasraftakip.database.entity.ExpenseEntity

class ExpenseRepository(private val appDao: AppDao) {

    suspend fun getAllExpenses(): List<ExpenseEntity> {
        return appDao.getAllExpenses()
    }

    suspend fun insertExpense(expense: ExpenseEntity) {
        appDao.insertExpense(expense)
    }

    suspend fun deleteExpense(expense: ExpenseEntity) {
        appDao.deleteExpense(expense)
    }

    suspend fun getExpensesBetweenDates(startDate: Long, endDate: Long): List<ExpenseEntity> {
        return appDao.getExpensesBetweenDates(startDate, endDate)
    }
}

