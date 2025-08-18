package com.fuat.aracmasraftakip.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fuat.aracmasraftakip.database.AppDatabase
import com.fuat.aracmasraftakip.database.entity.ExpenseEntity
import com.fuat.aracmasraftakip.database.repository.ExpenseRepository
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExpenseRepository

    init {
        val appDao = AppDatabase.getInstance(application).appDao()
        repository = ExpenseRepository(appDao)
    }

    fun insertExpense(expense: ExpenseEntity) = viewModelScope.launch {
        repository.insertExpense(expense)
    }

    fun deleteExpense(expense: ExpenseEntity) = viewModelScope.launch {
        repository.deleteExpense(expense)
    }

    fun getAllExpenses(onResult: (List<ExpenseEntity>) -> Unit) = viewModelScope.launch {
        val expenses = repository.getAllExpenses()
        onResult(expenses)
    }

    fun getExpensesBetweenDates(startDate: Long, endDate: Long, onResult: (List<ExpenseEntity>) -> Unit) = viewModelScope.launch {
        val expenses = repository.getExpensesBetweenDates(startDate, endDate)
        onResult(expenses)
    }
}

