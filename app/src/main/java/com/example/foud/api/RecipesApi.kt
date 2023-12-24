package com.example.foud.api

import com.example.foud.models.RecipesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RecipesApi {
    @GET("/recipes/complexSearch")
    suspend fun getRecipesList(@QueryMap queries: Map<String, String>): Response<RecipesList>

}