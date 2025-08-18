package com.fuat.aracmasraftakip.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey val id: Int = 1,
    @ColumnInfo(name = "currency") val currency: String, // Para birimi (tl, usd vb)
    @ColumnInfo(name = "year_filter") val yearFilter: Int //Görüntülenen yıl
)
