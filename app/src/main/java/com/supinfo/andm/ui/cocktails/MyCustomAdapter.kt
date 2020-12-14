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
import com.supinfo.andm.ui.cocktail_detail.CocktailDetailActivity
import com.supinfo.andm.utils.CocktailHelper

class MyCustomAdapter(context: Context, vm: CocktailsViewModel): BaseAdapter() {
    private val mContext: Context = context

    val names = CocktailHelper.getCocktailNames()

    override fun getCount(): Int {
        return names.size
    }

    override fun getItem(p0: Int): Any {
        return names[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(pos: Int, view: View?, viewGroup: ViewGroup?): View {

        val layoutInflater = LayoutInflater.from(mContext)
        val row = layoutInflater.inflate(R.layout.listview_item_row, viewGroup, false)

        row.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val intent = Intent(activity, CocktailDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // To pass any data to next activity
            intent.putExtra("name", CocktailHelper.getCocktailNames()[pos])
            intent.putExtra("image", CocktailHelper.getCocktailImages()[pos])
            // start your next activity
            ContextCompat.startActivity(activity, intent, null)
        }

        val cocktailName : TextView = row.findViewById(R.id.cocktailName)
        val cocktailImage : ImageView = row.findViewById(R.id.itemImageCocktail)

        cocktailName.text = names[pos]

        val uri = "@drawable/" + CocktailHelper.getCocktailImages()[pos] // where myresource (without the extension) is the file
        val drawableId: Int = mContext.getResources().getIdentifier(uri, "null", mContext.getPackageName())
        cocktailImage.setImageResource(drawableId)

        return row
    }
}
