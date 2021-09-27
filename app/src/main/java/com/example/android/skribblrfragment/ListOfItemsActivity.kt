package com.example.android.skribblrfragment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.skribblrfragment.databinding.ActivityListOfItemsBinding

class ListOfItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListOfItemsBinding
    private val TAG :String = "NewItemActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOfItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userInput = binding.newItemEditText
        val dataFromMain = intent.getStringExtra(INTENT_DATA_NAME)
        // set text from main to editText field
        userInput.setText(dataFromMain)

        binding.submitNewButton.setOnClickListener {
            val newUserInput = userInput.text.toString()
            //create new intent instance
            val intent = Intent()
            //send new task data to main activity
            intent.putExtra(INTENT_DATA_NAME, newUserInput)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}