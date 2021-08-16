package com.example.android.skribblr.data

import com.example.android.skribblr.R
import com.example.android.skribblr.model.Skribble

class Datasource {
    fun loadSkribbls() : List<Skribble>{
        return listOf<Skribble>(
            Skribble(R.string.skribble1,"Trash out","note"),
            Skribble(R.string.skribble2, "Washing", "note"),
            Skribble(R.string.skribble3, "Folding", "note"),
            Skribble(R.string.skribble4, "Tossing", "note"),
            Skribble(R.string.skribble5, "Trash out", "note")
        )
    }
}