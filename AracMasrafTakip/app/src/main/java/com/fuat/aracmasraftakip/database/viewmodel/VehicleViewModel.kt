package com.fuat.aracmasraftakip.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData                // ← Bunu ekledik
import com.fuat.aracmasraftakip.database.AppDatabase
import com.fuat.aracmasraftakip.database.entity.VehicleEntity
import com.fuat.aracmasraftakip.database.repository.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VehicleViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: VehicleRepository =
        VehicleRepository(
            AppDatabase
                .getInstance(getApplication())
                .appDao()
        )

    val allVehicles: LiveData<List<VehicleEntity>> = liveData(Dispatchers.IO) {
        emit(repository.getAllVehicles())
    }

    // Araç ekleme
    suspend fun insertVehicle(vehicle: VehicleEntity) {
        withContext(Dispatchers.IO) {
            repository.insertVehicle(vehicle)
        }
    }

    // Araç silme
    suspend fun deleteVehicle(vehicle: VehicleEntity) {
        withContext(Dispatchers.IO) {
            repository.deleteVehicle(vehicle)
        }
    }

    // Tüm araçları alma
    suspend fun getAllVehicles(): List<VehicleEntity> {
        return withContext(Dispatchers.IO) {
            repository.getAllVehicles()
        }
    }

    // Belirli bir aracı alma
    suspend fun getVehicleById(vehicleId: Int): VehicleEntity? {
        return withContext(Dispatchers.IO) {
            repository.getVehicleById(vehicleId)
        }
    }
}
