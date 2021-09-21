package com.example.android.skribblrfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.skribblrfragment.databinding.ActivityEditItemBinding
import com.example.android.skribblrfragment.model.SharedViewModel

class EditItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userInput = binding.editText
        val dataFromMain = intent.getStringExtra(INTENT_DATA_NAME)
        // Get the new task entered
        userInput.setText(dataFromMain)

        binding.submitEditButton.setOnClickListener {
            /*todo confirm why all text is deleted. confirm correct buttons are selected for editing*/

            // Clear the EditText field
            val edittedUserInput = userInput.text.toString()
            val intent = Intent()
            for (index in 0 until list.size) {
                //get index of item to be replaced
                if(userInput.equals(list[index])) {
                    //set it with new user inputted data
                    list[index] = SharedViewModel(edittedUserInput)
                }
            }
            intent.putExtra(INTENT_DATA_NAME, edittedUserInput)
            setResult(RESULT_OK, intent)
            userInput.text.clear()
            startActivity(Intent(this@EditItemActivity, MainActivity::class.java))
        }
    }
}