package com.fuat.aracmasraftakip.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fuat.aracmasraftakip.database.AppDatabase
import com.fuat.aracmasraftakip.database.entity.SettingsEntity
import com.fuat.aracmasraftakip.database.repository.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SettingsRepository

    init {
        val appDao = AppDatabase.getInstance(application).appDao()
        repository = SettingsRepository(appDao)
    }

    // Ayarları kaydetme
    suspend fun saveSettings(settings: SettingsEntity) {
        withContext(Dispatchers.IO) {
            repository.saveSettings(settings)
        }
    }

    // Ayarları alma
    suspend fun getSettings(): SettingsEntity? {
        return withContext(Dispatchers.IO) {
            repository.getSettings()
        }
    }
}
