package com.example.android.skribblrfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.android.skribblrfragment.data.DataSource
import com.example.android.skribblrfragment.databinding.ActivityEditItemBinding
import com.example.android.skribblrfragment.model.SharedViewModel

class EditItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditItemBinding
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userInput = binding.editText
        val dataFromMain = intent.getStringExtra(INTENT_DATA_NAME)
        // Get the new task entered
        userInput.setText(dataFromMain)

        binding.submitEditButton.setOnClickListener {
            //todo identify what is needed to actually update edited task

            // Clear the EditText field
            val editedUserInput = userInput.text.toString()
            val intent = Intent()
            for (index in 0 until viewModel.taskList.value!!.size) {
                //get index of item to be replaced
                if(userInput.equals(viewModel.taskList.value!![index])) {
                    //set it with new user inputted data
                    viewModel.taskList.value!![index] = DataSource(editedUserInput)
                }
            }
            intent.putExtra(INTENT_DATA_NAME, DataSource(editedUserInput).toString())
            setResult(RESULT_OK, intent)
            userInput.text.clear()
            startActivity(Intent(this@EditItemActivity, MainActivity::class.java))
        }
    }
}