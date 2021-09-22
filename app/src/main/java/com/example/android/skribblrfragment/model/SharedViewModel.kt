package com.example.android.skribblrfragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.skribblrfragment.data.DataSource

class SharedViewModel : ViewModel() {

    private var _userInput: String = "Sample text"
    private val userInput: String
        get() = _userInput

    private var _taskList: MutableList<String> = mutableListOf()
    val taskList: MutableList<String>
        get() = _taskList


    fun addNewTask(){
        _taskList = mutableListOf(userInput)
    }
}