package com.example.android.skribblrfragment.model

import androidx.lifecycle.ViewModel

class SharedViewModel(var notes: String) : ViewModel() {

    private var _userInput: String = "Sample text"
    val userInput: String
        get() = _userInput

    private var _taskList: MutableList<String> = mutableListOf()
    val taskList: MutableList<String>
        get() = _taskList

    //what data do i need?
    //user input is taken from new item or edit item and added to list
    //user input is not static, it is dynamic.
    //should add to mutable list
    //should mutable list be added  here
    //how can i access it to affect views?
    //use liveData and below to access list with recyclerview. reference test navigation transform viewmodel

    /*
    * private val _taskList = MutableLiveData<List<String>>().apply{
    *   value = (figure out what to enter here, maybe the strings from the strings.xml file to start)
    * }
    *
    * val taskList: LiveData<List<String>> = _taskList */

    fun addNewTask(){
        taskList.add(userInput)
    }

    fun removeTask(){
        taskList.remove(userInput)
    }
}