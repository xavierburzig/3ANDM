package com.supinfo.andm.adapters

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.supinfo.andm.R
import com.supinfo.andm.ui.cocktail_detail.CocktailDetailFragment
import com.supinfo.andm.utils.CocktailHelper


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerviewViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerviewViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recyclerview_item_row, viewGroup, false)
        return RecyclerviewViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: RecyclerviewViewHolder, i: Int) {
        viewHolder.cocktailName.text = CocktailHelper.getCocktailNames()[i]
        val uri = "@drawable/" + CocktailHelper.getCocktailImages()[i] // where myresource (without the extension) is the file
        val drawableId: Int = viewHolder.itemView.context.getResources().getIdentifier(uri, "null", viewHolder.itemView.context.getPackageName())
        viewHolder.cocktailImage.setImageResource(drawableId)
        viewHolder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
//                val context = viewHolder.itemView.context
                val activity = v!!.context as AppCompatActivity
                val cocktailDetailFragment = CocktailDetailFragment()
                val bundle = Bundle()
                bundle.putInt("POSITION", i)
                bundle.putString("NAME", CocktailHelper.getCocktailNames()[i])
                bundle.putInt("IMAGE", drawableId)
                cocktailDetailFragment.arguments = bundle

                activity.supportFragmentManager.beginTransaction().replace(
                    R.id.cocktails,
                    cocktailDetailFragment
                ).addToBackStack(null).setReorderingAllowed(true).commit()
            }
        })
    }

    override fun getItemCount(): Int {
        return CocktailHelper.getCocktailNames().size
    }

    class RecyclerviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cocktailName : TextView = itemView.findViewById(R.id.cocktailName)
        val cocktailImage : ImageView = itemView.findViewById(R.id.itemImageCocktail)
    }
}
