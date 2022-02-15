package com.thefear.myfilms.model.repository.forretrofit

import com.thefear.myfilms.model.repository.entitiesDTO.MoveDTO
import com.thefear.myfilms.model.repository.entitiesDTO.PageDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoveAPI {
    @GET("movie")
    fun getMove(
        @Query("field") ratingKP : String,
        @Query("search") ratRange : String,
        @Query("field") typeNumber : String,
        @Query("search") type : Int,
        @Query("field") year : String,
        @Query("search") years : String,
        @Query("token") token : String,
    ) : Call<PageDTO>
}