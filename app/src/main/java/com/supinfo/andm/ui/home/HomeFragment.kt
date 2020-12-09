package com.supinfo.andm.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.supinfo.andm.R
import com.supinfo.andm.ui.cocktail_detail.CocktailDetailFragment
import com.supinfo.andm.ui.cocktails.CocktailsFragment
import com.supinfo.andm.utils.CocktailHelper
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.welcome)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        discover.setOnClickListener { v ->  discoverClicked(v) }
        search.setOnClickListener { v ->  searchClicked(v) }
        context?.applicationContext?.let { GlideApp.with(it).load(R.drawable.giff).into(
            backgroundImageView
        ) }
    }

    private fun searchClicked(v: View?) {
        // context
        val activity = v!!.context as AppCompatActivity
        // fragments
        val cocktailsFragment = CocktailsFragment()
        val cocktailDetailFragment = CocktailDetailFragment()
        // recherche
        val result = CocktailHelper.searchCocktailByName(cocktail_input.text.toString())
        // si pas trouvé on affiche la liste
        if (result == -1) {
            v.hideKeyboard()
            activity.supportFragmentManager.beginTransaction().replace(
                (v.parent as ViewGroup).id,
                cocktailsFragment
            ).addToBackStack(null).setReorderingAllowed(true).commit()
        } else {
            // sinon l'image
            val uri = "@drawable/" + CocktailHelper.getCocktailImages()[result] // where myresource (without the extension) is the file
            val drawableId: Int = activity.getResources().getIdentifier(
                uri,
                "null",
                activity.getPackageName()
            )
            // on crée le Bundle qu'on récupere de l'autre coté
            val bundle = Bundle()
            bundle.putString("NAME", CocktailHelper.getCocktailNames()[result])
            bundle.putInt("IMAGE", drawableId)
            cocktailDetailFragment.arguments = bundle
            // on ferme le clavier
            v.hideKeyboard()
            // on navigue
            activity.supportFragmentManager.beginTransaction().replace(
                (v.parent as ViewGroup).id,
                cocktailDetailFragment
            ).addToBackStack(null).setReorderingAllowed(true).hide(this).commit()
        }
    }
    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
    private fun discoverClicked(v: View) {
        val activity = v.context as AppCompatActivity
        val cocktailsFragment = CocktailsFragment()
        activity.supportFragmentManager.beginTransaction().replace(
            R.id.homeFragment,
            cocktailsFragment
        ).addToBackStack(null).setReorderingAllowed(true).commit()
    }
}