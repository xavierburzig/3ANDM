package com.supinfo.andm.ui.cocktails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.supinfo.andm.R
import com.supinfo.andm.data.Cocktail
import com.supinfo.andm.ui.home.GlideApp

class MainRecyclerAdapter(val context: Context, val cocktails: List<Cocktail>, val itemListenner: CocktailItemListener): RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    override fun getItemCount() = cocktails.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.listview_item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cocktail = cocktails[position]
        with(holder) {
            cocktailText?.let { it.text = cocktail.strDrink}
            GlideApp.with(context).load(cocktail.strDrinkThumb).into(cocktailImage)
        }
        holder.itemView.setOnClickListener{
            itemListenner.onCocktailItemClick(cocktail)
        }
    }




    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cocktailText = itemView.findViewById<TextView>(R.id.cocktailName)
        val cocktailImage = itemView.findViewById<ImageView>(R.id.itemImageCocktail)
    }
    interface CocktailItemListener {
        fun onCocktailItemClick( cocktail: Cocktail)
    }

}