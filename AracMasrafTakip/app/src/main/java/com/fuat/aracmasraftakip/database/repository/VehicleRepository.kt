package com.fuat.aracmasraftakip.database.repository

import com.fuat.aracmasraftakip.database.AppDao
import com.fuat.aracmasraftakip.database.entity.VehicleEntity

class VehicleRepository(private val appDao: AppDao) {

    suspend fun getAllVehicles(): List<VehicleEntity> {
        return appDao.getAllVehicles()
    }

    suspend fun insertVehicle(vehicle: VehicleEntity) {
        appDao.insertVehicle(vehicle)
    }

    suspend fun deleteVehicle(vehicle: VehicleEntity) {
        appDao.deleteVehicle(vehicle)
    }

    suspend fun getVehicleById(vehicleId: Int): VehicleEntity? {
        return appDao.getVehicleById(vehicleId)
    }
}
