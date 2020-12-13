package com.supinfo.andm.ui.cocktail_detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.supinfo.andm.R
import com.supinfo.andm.data.Cocktail
import com.supinfo.andm.data.CocktailRepository
import com.supinfo.andm.ui.home.GlideApp
import kotlinx.android.synthetic.main.activity_cocktail_detail.*
import kotlinx.android.synthetic.main.activity_home.*


class CocktailDetailActivity : AppCompatActivity() {
    private lateinit var cocktailDetailViewModel: CocktailDetailViewModel
    private lateinit var cocktailRepo: CocktailRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_detail)

//        cocktail_name.text = intent.getStringExtra("name")

        val cocktail = intent.extras!!.get("cocktail") as Cocktail

//        val uri = "@drawable/" + intent.getStringExtra("image") // where myresource (without the extension) is the file
//        val drawableId: Int = this.getResources().getIdentifier(uri, "null", this.getPackageName())
//        cocktail_image.setImageResource(drawableId)
        cocktail_name.text = cocktail.strDrink
        cocktailRepo = CocktailRepository(this.application)
        if (cocktailRepo.networkAvailable()) {
            val imageView: ImageView = findViewById<View>(R.id.cocktail_image) as ImageView
            GlideApp.with(this).load(cocktail.strDrinkThumb).into(imageView);
        } else {
            val imageView: ImageView = findViewById<View>(R.id.cocktail_image) as ImageView
            GlideApp.with(this).load(cocktail.strImageSource).into(imageView);
        }

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