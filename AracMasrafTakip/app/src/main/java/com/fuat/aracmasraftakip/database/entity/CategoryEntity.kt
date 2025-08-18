package com.fuat.aracmasraftakip.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "icon") val icon: String? = null //isteğe ağlı bu
)
