package com.supinfo.andm.ui.home

import android.app.Application
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.supinfo.andm.R
import com.supinfo.andm.ui.cocktail_detail.CocktailDetailActivity
import com.supinfo.andm.ui.cocktails.CocktailsActivity
import com.supinfo.andm.utils.CocktailHelper


class HomeViewModel(app: Application) : AndroidViewModel(app)  {

    val welcome = MutableLiveData<String>()
    private var context: Context
    init {
        context = app
//      Initialize properties
        welcome.value = getApplication<Application>().getString(R.string.welcome)
    }

    fun discoverClicked() {
        val intent = Intent(context, CocktailsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(context, intent, null)
    }
    fun searchClicked(search_text: String) {
        // recherche
        val result = CocktailHelper.searchCocktailByName(search_text)
        // si pas trouvé on affiche la liste
        if (result == -1) {
//            hideKeyboard()
           discoverClicked()
        } else {
            // sinon l'image
            val uri = "@drawable/" + CocktailHelper.getCocktailImages()[result] // where myresource (without the extension) is the file
            val drawableId: Int = context.getResources().getIdentifier(
                uri,
                "null",
                context.getPackageName()
            )
            val intent = Intent(context, CocktailDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // To pass any data to next activity
            intent.putExtra("name", CocktailHelper.getCocktailNames()[result])
            intent.putExtra("image", CocktailHelper.getCocktailImages()[result])
            // on ferme le clavier

            // start your next activity
            startActivity(context, intent, null)
        }
    }
}