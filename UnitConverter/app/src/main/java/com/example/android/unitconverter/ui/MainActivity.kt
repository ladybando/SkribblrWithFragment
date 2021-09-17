package com.example.android.unitconverter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.unitconverter.R
import com.example.android.unitconverter.R.string.convert_results
import com.example.android.unitconverter.data.UnitsViewModel
import com.example.android.unitconverter.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Obtain ViewModel from ViewModelProviders
    private val viewModel by lazy {
        ViewModelProvider(this).get(UnitsViewModel::class.java)
    }

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //on creation input fields are blank
       fahrenheit_input.visibility = View.GONE
       celsius_input.visibility=View.GONE

        val binding : ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner=this
        binding.viewmodel=viewModel

       binding.imageButtonCels.setOnClickListener{
           fahrenheit_input.visibility = View.VISIBLE
       }
       binding.imageButtonFahr.setOnClickListener{
           celsius_input.visibility = View.VISIBLE
       }
        setListeners()
    }

    private fun setListeners(){
        //start calculations with convert click
        findViewById<Button>(R.id.convert_button).setOnClickListener { calcUnit() }
        //clear calculations with clear button and clear text view
        findViewById<Button>(R.id.clear_button).setOnClickListener {
            findViewById<EditText>(R.id.celsius_input).text = null
            findViewById<EditText>(R.id.fahrenheit_input).text = null
            findViewById<EditText>(R.id.celsius_input).visibility = View.INVISIBLE
            findViewById<EditText>(R.id.fahrenheit_input).visibility = View.INVISIBLE
        }
    }

    private fun calcUnit() {
        //get text stored in text field and assign to variable
        val stringFahrUnit = findViewById<EditText>(R.id.fahrenheit_input).text.toString()
        val stringCelsUnit = findViewById<EditText>(R.id.celsius_input).text.toString()
        //convert stored text to double for calculations
        var convertFahr = stringFahrUnit.toDoubleOrNull()
        var convertCels = stringCelsUnit.toDoubleOrNull()
        //perform calculations based on whichever field is selected
        if (findViewById<EditText>(R.id.fahrenheit_input).isFocusableInTouchMode && convertFahr != null) {
            convertFahr = (convertFahr - 32) * 5 / 9
            findViewById<EditText>(R.id.celsius_input).setText(
                getString(
                    convert_results,
                    convertFahr.toString()
                )
            )
        } else {
            if (convertCels != null) {
                convertCels = (convertCels * 9 / 5) + 32
                findViewById<EditText>(R.id.fahrenheit_input).setText(
                    getString(
                        convert_results,
                        convertCels.toString()
                    )
                )
            }
        }
    }
}