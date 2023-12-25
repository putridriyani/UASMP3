package com.example.foud.util

class Constants {

    // ! https://api.spoonacular.com/recipes/complexSearch?number=10&apiKey=e67fadea9d9546d184f421d0e49b22bf&type=fingerfood&diet=Vegan&addRecipeInformation=true&fillIngredients=true

    companion object{
        const val BASE_URL = "https://api.spoonacular.com"
        const val API_KEY = "1c8adb07bb1c4ca089d0a819096ad8df"
        const val IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"

        // ? api queries
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_NUMBER = "number"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_INFO = "addRecipeInformation"
        const val QUERY_INGREDIENTS = "fillIngredients"

        // ? room database
        const val DATABASE_NAME = "recipes_database"
        const val FAV_TABLE = "favorites_table"
    }
}