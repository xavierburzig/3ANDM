package com.supinfo.andm.ui.cocktail_detail

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.supinfo.andm.R
import com.supinfo.andm.ui.cocktails.CocktailsViewModel
import com.supinfo.andm.ui.home.GlideApp
import com.supinfo.andm.ui.home.HomeViewModel
import com.supinfo.andm.utils.CocktailHelper
import kotlinx.android.synthetic.main.activity_cocktail_detail.*
import kotlinx.android.synthetic.main.activity_home.*

class CocktailDetailActivity : AppCompatActivity() {
    private lateinit var cocktailDetailViewModel: CocktailDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_detail)

        cocktail_name.text = intent.getStringExtra("name")
        val uri = "@drawable/" + intent.getStringExtra("image") // where myresource (without the extension) is the file
        val drawableId: Int = this.getResources().getIdentifier(uri, "null", this.getPackageName())
        cocktail_image.setImageResource(drawableId)

        initViewModel()
    }

    /**
     * Initialize the viewModel
     */
    private fun initViewModel() {
        val provider: CocktailDetailViewModel by viewModels()
        cocktailDetailViewModel = provider
    }

}