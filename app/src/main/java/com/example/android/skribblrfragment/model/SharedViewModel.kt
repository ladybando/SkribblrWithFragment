package com.example.android.skribblrfragment.model

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _userInput: String = "Sample text"
    val userInput: String
        get() = _userInput

    private var _taskList: MutableList<String> = mutableListOf()
    val taskList: MutableList<String>
        get() = _taskList
}