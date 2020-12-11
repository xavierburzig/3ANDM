package com.supinfo.andm.ui.cocktails

import android.os.Bundle
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.supinfo.andm.R

class CocktailsActivity : AppCompatActivity() {

    private lateinit var cocktailsViewModel: CocktailsViewModel
    private lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktails)
        initViewModel()
    }

    /**
     * Initialize the viewModel
     */
    private fun initViewModel() {
        val provider: CocktailsViewModel by viewModels()
        cocktailsViewModel = provider
        // init the listview
        listView = findViewById(R.id.cocktails_listview)
        val adapter = MyCustomAdapter(this, cocktailsViewModel)
        listView.adapter = adapter
    }
}