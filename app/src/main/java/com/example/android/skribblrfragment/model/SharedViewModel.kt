package com.example.android.skribblrfragment.model

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _userInput: String = "Sample text"
    private val userInput: String
        get() = _userInput

    private var _taskList: MutableList<String> = mutableListOf()
    val taskList: MutableList<String>
        get() = _taskList

    var listPosition : Int = -1

    fun addNewTask(){
        _taskList = mutableListOf(userInput)
    }
}