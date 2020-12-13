package com.supinfo.andm.ui.home

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.supinfo.andm.R
import com.supinfo.andm.data.CocktailRepository
import com.supinfo.andm.ui.cocktail_detail.CocktailDetailActivity
import com.supinfo.andm.ui.cocktails.CocktailsActivity
import com.supinfo.andm.ui.cocktails.CocktailsViewModel


class HomeViewModel(app: Application) : AndroidViewModel(app) {

    val welcome = MutableLiveData<String>()
    val cocktailRepo: CocktailRepository = CocktailRepository(app)
    val cocktailViewModel: CocktailsViewModel = CocktailsViewModel(app)
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
        if (cocktailRepo.networkAvailable()) {
            val result =
                cocktailRepo.searchCocktailByName(search_text, cocktailViewModel.cocktailData)
            if (result == -1) {
//            hideKeyboard()
                discoverClicked()
            } else {
                // sinon l'image
//            val uri = "@drawable/" + CocktailHelper.getCocktailImages()[result] // where myresource (without the extension) is the file
//            val drawableId: Int = context.getResources().getIdentifier(
//                uri,
//                "null",
//                context.getPackageName()
//            )
                val intent = Intent(context, CocktailDetailActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // To pass any data to next activity
//            intent.putExtra("name", CocktailHelper.getCocktailNames()[result])
//            intent.putExtra("image", CocktailHelper.getCocktailImages()[result])
                intent.putExtra("cocktail", cocktailViewModel.cocktailData.value?.get(result))

                // on ferme le clavier

                // start your next activity
                startActivity(context, intent, null)
            }
        } else {
            val result = cocktailRepo.searchCocktailByName(search_text)
            if (result == -1) {
//            hideKeyboard()
                discoverClicked()
            } else {
                // sinon l'image
//            val uri = "@drawable/" + CocktailHelper.getCocktailImages()[result] // where myresource (without the extension) is the file
//            val drawableId: Int = context.getResources().getIdentifier(
//                uri,
//                "null",
//                context.getPackageName()
//            )
                val intent = Intent(context, CocktailDetailActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // To pass any data to next activity
//            intent.putExtra("name", CocktailHelper.getCocktailNames()[result])
//            intent.putExtra("image", CocktailHelper.getCocktailImages()[result])
//                intent.putExtra("cocktail", cocktailViewModel.cocktailData.value?.get(result))

                // on ferme le clavier

                // start your next activity
                startActivity(context, intent, null)
            }
            // si pas trouvé on affiche la liste

        }
    }
}