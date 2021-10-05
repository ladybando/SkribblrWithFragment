package com.example.android.skribblrfragment.model

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private var _taskList: MutableList<String> = mutableListOf()
    val taskList: MutableList<String>
        get() = _taskList

    var listPosition : Int = -1
}