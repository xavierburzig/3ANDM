package com.supinfo.andm.ui.cocktails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.supinfo.andm.data.Cocktail
import com.supinfo.andm.data.CocktailRepository


class CocktailsViewModel(app: Application) : AndroidViewModel(app)  {


    private val dataRepo = CocktailRepository(app)
    val cocktailData = dataRepo.cocktailData

    init {
        // to retrieve from raw
//        val text = FileHelper.getTextFromResources(app, R.raw.cocktail_data)
    }

    fun refreshData() {
        dataRepo.refreshData()
    }
}
