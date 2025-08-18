package com.fuat.aracmasraftakip.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "expenses",

    foreignKeys = [
        ForeignKey(
            entity = VehicleEntity::class,
            parentColumns = ["id"],
            childColumns = ["vehicle_id"],
            onDelete = ForeignKey.CASCADE //araç silinirse masrafları da silinir ondan bağladık.
        )
    ],
    indices = [Index(value = ["vehicle_id"])] //performans arttırmak için index (aramalarda)

    )
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id : Int = 0, // Masraf ID
    @ColumnInfo(name = "vehicle_id") val vehicleId: Int, // Araç ID'si
    @ColumnInfo(name="category") val category: String, // Masraf Kategorisi
    @ColumnInfo(name="amount") val amount: Double, // Masraf Miktarı
    @ColumnInfo(name="date") val date: Long, // Tarih (timestamp)
    @ColumnInfo(name="note") val note: String? = null //nullable olabilir, zorunlu alan değil
)
