package com.example.android.skribblrfragment.model

import androidx.lifecycle.ViewModel

class SharedViewModel(var notes: String) : ViewModel() {

    private var _userInput: String = "Sample text"
    val userInput: String
        get() = _userInput

    private var _taskList: MutableList<SharedViewModel> = mutableListOf()
    val taskList: MutableList<SharedViewModel>
        get() = _taskList

    fun addNewTask(){
        taskList.add(SharedViewModel(userInput))
    }

    fun removeTask(){
        taskList.remove(SharedViewModel(userInput))
    }
}