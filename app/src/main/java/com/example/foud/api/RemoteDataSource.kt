package com.example.foud.api

import com.example.foud.models.RecipesList
import retrofit2.Response
import retrofit2.http.QueryMap
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val recipesApi: RecipesApi) {
    suspend fun getRecipesList(queries: Map<String, String>) : Response<RecipesList>{
        return recipesApi.getRecipesList(queries)
    }

}