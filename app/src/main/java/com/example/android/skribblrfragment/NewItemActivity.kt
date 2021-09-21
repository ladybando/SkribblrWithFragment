package com.example.android.skribblrfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.android.skribblrfragment.data.DataSource
import com.example.android.skribblrfragment.databinding.ActivityNewItemBinding
import com.example.android.skribblrfragment.model.SharedViewModel

class NewItemActivity : AppCompatActivity() {
    private val viewModel: SharedViewModel by viewModels()
    private lateinit var binding: ActivityNewItemBinding
    private val TAG :String = "NewItemActivity"
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
            Log.d(TAG, "New user input: $newUserInput")
            //returns user text
            val intent = Intent()
            Log.d(TAG, "TaskList size: $viewModel.taskList.size")
            //returns reference not actual task size
            for (index in 0 until viewModel.taskList.value!!.size) {
                Log.d(TAG, "TaskList size inside for loop: $viewModel.taskList.size")
                //does nothing
                //get index of item to be replaced
                if(userInput.equals(viewModel.taskList.value!![index])) {
                    //set it with new user inputted data
                    viewModel.taskList.value!![index] = DataSource(newUserInput)
                    Log.d("NewItemActivity", " Value inside for loop: $viewModel.taskList[index]")
                    //does nothing
                }
                Log.d(TAG, "TaskList size outside for loop: $viewModel.taskList.size")
                //returns nothing
                Log.d("NewItemActivity", " Outside for loop: $viewModel.taskList[index]")
                //returns nothing
            }
            //send new task data to main activity
            intent.putExtra(INTENT_DATA_NAME, DataSource(newUserInput).toString())
            setResult(RESULT_OK, intent)
            userInput.text.clear()
            viewModel.taskList.value!!.add(DataSource(newUserInput))
            startActivity(Intent(this@NewItemActivity, MainActivity::class.java))
        }
    }
}