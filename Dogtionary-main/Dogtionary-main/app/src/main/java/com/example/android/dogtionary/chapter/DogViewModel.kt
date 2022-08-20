package com.example.android.dogtionary.chapter

import androidx.lifecycle.*
import com.example.android.dogtionary.data.Dog
import com.example.android.dogtionary.data.DogDao
import com.example.android.dogtionary.network.DogPhoto
import com.example.android.dogtionary.network.DogPhotoApi
import kotlinx.coroutines.launch

class DogViewModel(private val dogDao: DogDao) : ViewModel() {

    private val _dogPhoto = MutableLiveData<DogPhoto>()
    val dogPhoto: LiveData<DogPhoto> = _dogPhoto

    private val _status = MutableLiveData<String>()
    val status:LiveData<String> = _status

    init {
        getNewPhoto()
    }

    fun getNewPhoto() {
        try {
            viewModelScope.launch {
                val response = DogPhotoApi.retrofitService.getRandomPhoto()
                _dogPhoto.value = response
                addNewImage(response.imageUrl.toString())
            }
        } catch (e: Exception) {
            "Failure: ${e.message}"
        }
    }

    fun getPhotoByBreed(breedType: String?) {
        try {
            viewModelScope.launch {
                val response = DogPhotoApi.retrofitService.getPhotoByBreed(breedType!!)
                _dogPhoto.value = response
                _status.value = response.statusResponse!!
            }
        } catch (e: Exception) {
            "Failure: ${e.message}"
        }
    }

    fun getImageList(){
        dogDao.getAllImages()
    }

    private fun insertImage(dog: Dog){
        viewModelScope.launch {
            dogDao.insert(dog)
        }
    }

    private fun getNewImage(imageUrl: String) : Dog{
        return Dog(imageUrl = imageUrl)
    }

    private fun addNewImage(imageUrl: String){
        val newImage = getNewImage(imageUrl)
        insertImage(newImage)
    }
}

class DogViewModelFactory(private val dogDao: DogDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DogViewModel(dogDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
