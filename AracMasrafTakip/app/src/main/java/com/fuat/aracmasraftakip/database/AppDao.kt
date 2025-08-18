package com.fuat.aracmasraftakip.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fuat.aracmasraftakip.database.entity.CategoryEntity
import com.fuat.aracmasraftakip.database.entity.ExpenseEntity
import com.fuat.aracmasraftakip.database.entity.SettingsEntity
import com.fuat.aracmasraftakip.database.entity.VehicleEntity

@Dao
interface AppDao {

    // Expense Queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntity)

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    suspend fun getAllExpenses(): List<ExpenseEntity>

    @Query("SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate")
    suspend fun getExpensesBetweenDates(startDate: Long, endDate: Long): List<ExpenseEntity>

    // Category Queries
    @Query("SELECT * FROM categories WHERE id = :categoryId")
    suspend fun getCategoryById(categoryId: Int): CategoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Query("SELECT * FROM categories ORDER BY name ASC")
    suspend fun getAllCategories(): List<CategoryEntity>

    // Settings Queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSettings(settings: SettingsEntity)

    @Query("SELECT * FROM settings WHERE id = 1")
    suspend fun getSettings(): SettingsEntity?

    // Vehicle Queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: VehicleEntity)

    @Delete
    suspend fun deleteVehicle(vehicle: VehicleEntity)

    @Query("SELECT * FROM vehicles ORDER BY brand ASC")
    suspend fun getAllVehicles(): List<VehicleEntity>

    @Query("SELECT * FROM vehicles WHERE id = :vehicleId")
    suspend fun getVehicleById(vehicleId: Int): VehicleEntity?
}
