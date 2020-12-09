package com.supinfo.andm.ui.cocktail_detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.supinfo.andm.R
import com.supinfo.andm.interfaces.BackPressListener
import com.supinfo.andm.ui.cocktails.CocktailsFragment
import kotlinx.android.synthetic.main.fragment_cocktails.*
import kotlinx.android.synthetic.main.fragment_detail_cocktail.*
import kotlinx.android.synthetic.main.fragment_home.*


class CocktailDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_detail_cocktail, container, false)
        return root
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            val i = bundle.getInt("POSITION")
            cocktail_name.text = bundle.getString("NAME")
            cocktail_image.setImageResource(bundle.getInt("IMAGE"))
        }
    }
}