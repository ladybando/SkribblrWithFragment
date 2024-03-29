package com.example.android.dogtionary

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.android.dogtionary.chapter.DogViewModel
import com.example.android.dogtionary.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: DogViewModel by viewModels()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.dog_display_container) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.options_menu, menu)

            val search = menu.findItem(R.id.app_bar_search)
            searchView = search.actionView as SearchView

            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            (searchView).apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
            }

            //change search icon color
            val searchImgId = resources.getIdentifier("android:id/search_button", null, null)
            val searchIcon = searchView.findViewById<ImageView>(searchImgId)
            searchIcon.setImageResource(R.drawable.ic_breed_search)

            //change close button color
            val closeImgId = resources.getIdentifier("android:id/search_close_btn", null, null)
            val closeIcon = searchView.findViewById<ImageView>(closeImgId)
            closeIcon.setImageResource(R.drawable.ic_clear)

            //change EditText text color
            val searchTextId = searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
            val textView = searchView.findViewById<TextView>(searchTextId)
            textView.setTextColor(Color.BLACK)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(userQuery: String?): Boolean {
                    //Called when this view wants to give up focus
                    searchView.clearFocus()
                    //Returns the query string currently in the text field. checks if string is empty?
                    searchView.setQuery("", false)
                    search.collapseActionView()
                    if (viewModel.status.value.equals("error")) {
                        Snackbar
                            .make(this@MainActivity,
                                binding.cl,
                                "Rotten doggy treats, try another search term!",
                                Snackbar.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel.getPhotoByBreed(userQuery)
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }
            })
            return true
        }


}
