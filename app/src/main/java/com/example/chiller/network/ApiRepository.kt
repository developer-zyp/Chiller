package com.example.chiller.network

import com.example.chiller.model.BarInfo

class ApiRepository {

    private val api = ApiClient.apiService

    suspend fun getBarList(page: String): List<BarInfo> {
        return api.getBarList().body() ?: emptyList()
    }

    suspend fun getSearchBar(query: String): List<BarInfo> {
        return api.getSearchBar(query).body() ?: emptyList()
    }


//    region comment
//    companion object {
//        var movieData = MutableLiveData<Movie>()
//        fun getMovieApiCall(callback: (List<Movie>?) -> Unit) {
//            val call = apiServiceInterface.getMovie("1")
//            call.enqueue(object : Callback<List<Movie>> {
//                override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
//                    callback(response.body())
//                }
//
//                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
//                    callback(null)
//                }
//            })
//        }
//    }
//    endregion

}