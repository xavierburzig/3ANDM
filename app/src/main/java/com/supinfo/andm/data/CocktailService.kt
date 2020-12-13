package com.supinfo.andm.data

import com.squareup.moshi.Json
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CocktailService {
    //lizn utile pour bien comprendre
    //https://stackoverflow.com/questions/52787794/how-to-get-gson-retrofit-to-serialize-and-map-an-array-from-a-json-response-in
    // suspend means has to be call from coroutine
    @GET("search.php?s=v")
    suspend fun getCocktailData(): Response<CocktailsResponse>

    data class CocktailsResponse(
        @Json(name = "drinks") val drinks: List<Cocktail>
    )
}