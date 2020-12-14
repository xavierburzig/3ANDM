package com.supinfo.andm.utils

class CocktailHelper private constructor() {

    private object HOLDER {
        val INSTANCE = CocktailHelper()
    }

    companion object {

        val instance: CocktailHelper by lazy { HOLDER.INSTANCE }

        private val cocktailNames = mutableListOf(
            "Margarita",
            "Sex on the beach", "Pinacolada", "Panama",
            "Curacao Punch", "Paradise"
        )
        private val cocktailImages = mutableListOf(
            "margarita", "sex_on_the_beach", "pinacolada", "panama",
            "curacaopunch", "paradise"
        )

        fun getCocktailNames(): MutableList<String> {
            return cocktailNames
        }

        fun getCocktailImages(): MutableList<String> {
            return cocktailImages
        }

//        private fun getCocktailByIndex(id: Int): String {
//            return getCocktailNames()[id]
//        }
//
//        private fun getImageByIndex(id: Int): String {
//            return getCocktailNames()[id]
//        }

        fun searchCocktailByName(name: String): Int {
            return getCocktailNames().indexOf(getCocktailNames().find { cocktail_name -> name.equals(cocktail_name) })
        }
    }
}
