package com.fuat.aracmasraftakip.database.repository

import com.fuat.aracmasraftakip.database.AppDao
import com.fuat.aracmasraftakip.database.entity.SettingsEntity

class SettingsRepository(private val appDao: AppDao) {

    suspend fun saveSettings(settings: SettingsEntity) {
        appDao.saveSettings(settings)
    }

    suspend fun getSettings(): SettingsEntity? {
        return appDao.getSettings()
    }
}
