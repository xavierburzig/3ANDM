package com.supinfo.andm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CocktailDao {
    @Query("SELECT * FROM cocktails")
    fun getAll(): List<Cocktail>

    @Insert
    suspend fun insertCocktail(cocktail: Cocktail)

    @Insert
    suspend fun insertCocktails(cocktails: List<Cocktail>)

    @Query("DELETE from cocktails")
    suspend fun deleteAll()

}