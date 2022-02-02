package com.thefear.myfilms.model.repository.forretrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoveRepo {
    val adapter: MoveAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHTTPBuilderWithoutHeaders())
            .build()

        adapter.create(MoveAPI::class.java)
    }
}