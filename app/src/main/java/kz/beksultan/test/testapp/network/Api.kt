package kz.beksultan.test.testapp.network

import kz.beksultan.test.testapp.network.Model.MoviesResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("top")
    suspend fun getGamesList(
        @Query("api_key") api_key:String
    ): Response<MoviesResponse>

    companion object {
        fun create():Api{
            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/list/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}