package com.unsoed.responsi1mobileh1d023076.data.network

import com.unsoed.responsi1mobileh1d023076.data.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface Api {
    @GET("teams/{id}")
    fun getTeamDetail(
        @Path("id") teamId: Int,
        @Header("X-Auth-Token") token: String
    ): Call<SearchResponse>
}