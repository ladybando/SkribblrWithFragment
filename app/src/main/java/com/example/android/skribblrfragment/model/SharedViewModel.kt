package com.example.android.skribblrfragment.model

import androidx.lifecycle.ViewModel

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

<<<<<<< HEAD
}
=======
}
>>>>>>> c8c57705b3cf3378fba7f3fed83449f3c88ca149
