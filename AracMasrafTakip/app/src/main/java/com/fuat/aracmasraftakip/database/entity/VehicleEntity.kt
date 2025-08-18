package com.fuat.aracmasraftakip.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "vehicles")
data class VehicleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, //Araç ID
    @ColumnInfo(name = "brand") val brand: String, //Marka
    @ColumnInfo(name = "model") val model: String, //Model
    @ColumnInfo(name = "year") val year: Int?, // Üretim Yılı (isteğe bağlı)
    @ColumnInfo(name = "plate") val plate: String? //Plaka (isteğe bağlı)

)
