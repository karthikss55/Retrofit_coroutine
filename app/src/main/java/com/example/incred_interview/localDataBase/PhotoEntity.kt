package com.example.incred_interview.localDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "photoentity")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true)
     var uid:Int,
    @ColumnInfo(name = "title") var imageUrl: String
)