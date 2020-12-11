package com.supinfo.andm.ui.cocktail_detail

import android.app.Application
import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.supinfo.andm.R

class CocktailDetailViewModel (app: Application) : AndroidViewModel(app)  {
    private var context: Context
    init {
        context = app
    }
}