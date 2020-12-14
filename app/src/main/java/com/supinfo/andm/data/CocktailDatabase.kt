package com.supinfo.andm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
// entities = list d'entité , version si on update la structure plus tard on augmente la version, export schema on connait
@Database(entities = [Cocktail::class], version = 1, exportSchema = false)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao

    companion object {
        //can be accessed by multiple thread at a time
        @Volatile
        private var INSTANCE: CocktailDatabase? = null
        fun getDatabase(context: Context): CocktailDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CocktailDatabase::class.java,
                        "cocktails.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}