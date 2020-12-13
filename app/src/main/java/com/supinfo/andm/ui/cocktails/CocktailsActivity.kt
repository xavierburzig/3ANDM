package com.supinfo.andm.ui.cocktails

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.supinfo.andm.R
import com.supinfo.andm.data.Cocktail
import com.supinfo.andm.ui.cocktail_detail.CocktailDetailActivity
import com.supinfo.andm.utils.LOG_TAG
import java.io.Serializable

class CocktailsActivity : AppCompatActivity(), MainRecyclerAdapter.CocktailItemListener {

    private lateinit var cocktailsViewModel: CocktailsViewModel
//    private lateinit var listView: ListView
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktails)
        initViewModel()
    }

    /**
     * Initialize the viewModel
     */
    private fun initViewModel() {
        val provider: CocktailsViewModel by viewModels()
        cocktailsViewModel = provider
        //recycler view
        recyclerView = findViewById(R.id.recyclerView)
        cocktailsViewModel.cocktailData.observe(this, Observer {
            for (cocktail in it) {
                Log.i("cocktail","${cocktail.strDrink} (\$${cocktail.strDrinkZHS})")
            }
            val adapter = MainRecyclerAdapter(this, it, this)
            recyclerView.adapter = adapter
            swipeLayout.isRefreshing = false

        })
        //swipeLayout
        swipeLayout = findViewById(R.id.swipeLayout)
        swipeLayout.setOnRefreshListener {
            cocktailsViewModel.refreshData()
        }
        // init the listview
//        listView = findViewById(R.id.cocktails_listview)
//        val adapter = MyCustomAdapter(this, cocktailsViewModel, cocktailsViewModel.cocktailsList)
//        listView.adapter = adapter
    }

    override fun onCocktailItemClick(cocktail: Cocktail) {
        Log.i(LOG_TAG, "Selected cocktail: ${cocktail.strDrink}")
        val intent = Intent(this, CocktailDetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // To pass any data to next activity
//            intent.putExtra("name", CocktailHelper.getCocktailNames()[pos])
//            intent.putExtra("image", CocktailHelper.getCocktailImages()[pos])
        intent.putExtra("cocktail", cocktail as Serializable) // pour passer un objet
        // start your next activity
        this.startActivity(intent, null)
    }
}