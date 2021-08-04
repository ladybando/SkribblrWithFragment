package com.example.android.unitconverter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners(){
        //hide textview until image button is clicked
        binding.celsiusInput.visibility = View.INVISIBLE
        binding.fahrenheitInput.visibility = View.INVISIBLE
        //show fields onclick
        binding.imageButtonCels.setOnClickListener{
            binding.celsiusInput.visibility = View.VISIBLE
            binding.fahrenheitInput.visibility = View.VISIBLE
        }
        binding.imageButtonFahr.setOnClickListener{
            binding.celsiusInput.visibility = View.VISIBLE
            binding.fahrenheitInput.visibility = View.VISIBLE
        }
        //start calculations with convert click
        binding.convertButton.setOnClickListener { calcUnit() }
        //clear calculations with clear button and clear text view
        binding.clearButton.setOnClickListener {
            binding.celsiusInput.text = null
            binding.fahrenheitInput.text = null
            binding.celsiusInput.visibility = View.INVISIBLE
            binding.fahrenheitInput.visibility = View.INVISIBLE
        }
    }

    private fun calcUnit() {
        //get text stored in text field and assign to variable
        val stringFahrUnit = binding.fahrenheitInput.text.toString()
        val stringCelsUnit = binding.celsiusInput.text.toString()
        //convert stored text to double for calculations
        var convertFahr = stringFahrUnit.toDoubleOrNull()
        var convertCels = stringCelsUnit.toDoubleOrNull()
        //perform calculations based on whichever field is selected
        if (binding.fahrenheitInput.isFocusableInTouchMode && convertFahr != null) {
            convertFahr = (convertFahr - 32) * 5 / 9
            binding.celsiusInput.setText(
                getString(
                    R.string.convert_results,
                    convertFahr.toString()
                )
            )
        } else {
            if (convertCels != null) {
                convertCels = (convertCels * 9 / 5) + 32
                binding.fahrenheitInput.setText(
                    getString(
                        R.string.convert_results,
                        convertCels.toString()
                    )
                )
            }
        }
    }
}