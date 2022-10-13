package com.example.pagingtest.api

import com.example.pagingtest.model.ResponseRickMorty
import com.example.pagingtest.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getAllCharacters(@Query("page") page: Int): Response<ResponseRickMorty>
}