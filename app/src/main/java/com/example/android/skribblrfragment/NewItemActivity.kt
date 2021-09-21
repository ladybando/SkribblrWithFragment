package com.example.android.skribblrfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.skribblrfragment.databinding.ActivityNewItemBinding
import com.example.android.skribblrfragment.model.SharedViewModel

class NewItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userInput = binding.newEditText
        val dataFromMain = intent.getStringExtra(INTENT_DATA_NAME)
        // Get the task entered
        userInput.setText(dataFromMain)

        binding.submitNewButton.setOnClickListener {
            val newUserInput = userInput.text.toString()
            val intent = Intent()
            for (index in 0 until list.size) {
                //get index of item to be replaced
                if(userInput.equals(list[index])) {
                    //set it with new user inputted data
                    list[index] = SharedViewModel(newUserInput)
                }
            }
            //send new task data to main activity
            intent.putExtra(INTENT_DATA_NAME, newUserInput)
            setResult(RESULT_OK, intent)
            userInput.text.clear()
            list.add(SharedViewModel(newUserInput))
            startActivity(Intent(this@NewItemActivity, MainActivity::class.java))
        }
    }
}