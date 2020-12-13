package com.supinfo.andm.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.supinfo.andm.utils.CocktailHelper
import com.supinfo.andm.utils.FileHelper
import com.supinfo.andm.utils.LOG_TAG
import com.supinfo.andm.utils.WEB_SERVICE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class CocktailRepository(val app: Application) {

    val cocktailData = MutableLiveData<List<Cocktail>>()

    private val listType = Types.newParameterizedType(
        List::class.java, Cocktail::class.java
    )
    init {
//        getCocktailData()
        Log.i("cocktails", "Network available: ${networkAvailable()}")
        //background
        refreshData()

    }
    // background thread
    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable()) {
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl(WEB_SERVICE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                val service = retrofit.create(CocktailService::class.java)
                val servideData = service.getCocktailData().body()?.drinks ?: emptyList()
                for (drink in servideData) {
                    Log.i(LOG_TAG, "drinks  : ${drink.strDrink}")
                }
                cocktailData.postValue(servideData)
            } catch (e : Exception) {
                Log.e(LOG_TAG, e.message.toString())
            }

        }
    }


    // local call
    fun getCocktailData() {
        val text = FileHelper.getTextFromAssets(app, "cocktail_data.json")
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Cocktail>> = moshi.adapter(listType)
        cocktailData.value = adapter.fromJson(text) ?: emptyList()
    }

    @Suppress("DEPRECATION")
    fun networkAvailable(): Boolean {
        val cm = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun refreshData() {
        if (networkAvailable()) {
            CoroutineScope(Dispatchers.IO).launch {
                callWebService()
            }
        } else {
            getCocktailData()
        }
    }

    fun searchCocktailByName(name: String, list: MutableLiveData<List<Cocktail>>? = null): Int {
        if (networkAvailable()) {
            return list!!.value!!.indexOf(list.value!!.find { cocktail -> cocktail.strDrink.equals(name) })
        } else {
            return CocktailHelper.getCocktailNames()
                .indexOf(CocktailHelper.getCocktailNames().find { cocktail_name -> name.equals(cocktail_name) })
        }
    }
}