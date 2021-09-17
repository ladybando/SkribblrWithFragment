package com.example.android.unitconverter.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//TODO check what happens if both boxes have values or if both boxes are empty
class UnitsViewModel : ViewModel() {
    var fahrenheit = 0.0
    var celsius = 0.0
        private set
    private val _clear = MutableLiveData("Clear")
    val clear: LiveData<String> = _clear

    fun onClick(){
        fahrenheit.toString()
        celsius.toString()
    }

    fun getFahrInput(input:Double) {
        fahrenheit = input
    }

    fun getCelsInput(input: Double){
        celsius = input
    }
}