package com.supinfo.andm.ui.cocktails

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.supinfo.andm.R
import com.supinfo.andm.data.Cocktail
import com.supinfo.andm.ui.cocktail_detail.CocktailDetailActivity
import com.supinfo.andm.ui.home.GlideApp
import com.supinfo.andm.utils.CocktailHelper
import java.io.Serializable

class MyCustomAdapter(context: Context, vm: CocktailsViewModel, cocktails: List<Cocktail>): BaseAdapter() {
    private val mContext: Context = context
    private val cocktails: List<Cocktail> = cocktails

//    val names = CocktailHelper.getCocktailNames()

    override fun getCount(): Int {
        return cocktails.size
    }

    override fun getItem(p0: Int): Any {
        return cocktails[p0]
    }

    override fun getItemId(p0: Int): Long {
        return cocktails.indexOf(cocktails[p0]).toLong()
    }

    override fun getView(pos: Int, view: View?, viewGroup: ViewGroup?): View {

        val layoutInflater = LayoutInflater.from(mContext)
        val row = layoutInflater.inflate(R.layout.listview_item_row, viewGroup, false)

        row.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val intent = Intent(activity, CocktailDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // To pass any data to next activity
//            intent.putExtra("name", CocktailHelper.getCocktailNames()[pos])
//            intent.putExtra("image", CocktailHelper.getCocktailImages()[pos])
            intent.putExtra("cocktail", cocktails[pos] as Serializable) // pour passer un objet
            // start your next activity
            ContextCompat.startActivity(activity, intent, null)
        }

        val cocktailName : TextView = row.findViewById(R.id.cocktailName)
        val cocktailImage : ImageView = row.findViewById(R.id.itemImageCocktail)

        cocktailName.text = cocktails[pos].strDrink

        GlideApp.with(mContext).load(cocktails[pos].strImageSource).into(cocktailImage);

//        val uri = "@drawable/" + CocktailHelper.getCocktailImages()[pos] // where myresource (without the extension) is the file
//        val drawableId: Int = mContext.getResources().getIdentifier(uri, "null", mContext.getPackageName())
//        cocktailImage.setImageResource(drawableId)

        return row
    }
}
