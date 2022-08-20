package com.example.android.dogtionary.application

import android.app.Application
import com.example.android.dogtionary.data.DogRoomDatabase

class DogImageApplication : Application() {
    val database: DogRoomDatabase by lazy { DogRoomDatabase.getDatabase(this) }
}