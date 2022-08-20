package com.example.android.dogtionary.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class Dog(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "image")
    val imageUrl: String,

    @ColumnInfo(name = "previous")
    val previous: String = "previous",

    @ColumnInfo(name = "current")
    val current: String = "current",
    //have another table with previous and next and set image url to one or the other and then use update to display

)