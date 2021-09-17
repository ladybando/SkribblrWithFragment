package com.example.android.unitconverter.util

import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.unitconverter.R
import java.io.DataInput

//view empty input fields on button press
@BindingAdapter("goneUnless")
fun goneUnless(imageButton: ImageButton, view: TextView){
    imageButton.setOnClickListener{
        view.visibility = View.VISIBLE}
}
//display answer to math calculation with set text
@BindingAdapter("android:text")
fun setText(view: TextView, text:CharSequence) {
    view.text = text
}