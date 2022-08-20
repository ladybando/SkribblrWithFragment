package com.example.android.dogtionary.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.*
import com.example.android.dogtionary.chapter.DogViewModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dog: Dog)

    @Update
    suspend fun update(dog: Dog)

    @Query("SELECT * FROM images")
    fun getAllImages(): List<Dog>
}