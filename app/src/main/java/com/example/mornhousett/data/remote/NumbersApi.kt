package com.example.mornhousett.data.remote

import com.example.mornhousett.model.Fact
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NumbersApi {

    @GET("random/math")
    @Headers("Content-Type:application/json")
    suspend fun getRandomFact(): Fact

    @GET("{number}")
    @Headers("Content-Type:application/json")
    suspend fun getFactByNumber(
        @Path("number") number: Int
    ): Fact


}