package com.example.chiller.network

import com.example.chiller.model.BarInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    @POST("/search")
//    suspend fun getBarList(
//        @Query("language") language: String = "en_US",
//        @Query("limit") limit: String = "30",
//        @Query("location_id") location_id: String = "294191",
//        @Query("currency") currency: String = "MMK"
//    ): Response<BarResponse>

    @GET("api/DataSync/GetRestaurantInfo")
    suspend fun getBarList(): Response<List<BarInfo>>

    @GET("api/DataSync/GetSearchBar")
    suspend fun getSearchBar(@Query("query") search: String): Response<List<BarInfo>>

//    @POST("/api/sync")
//    fun setData(@Body data: ApiHelper) : Call<ResponseBody>
}