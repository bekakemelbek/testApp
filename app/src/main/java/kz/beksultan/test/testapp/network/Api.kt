package kz.beksultan.test.testapp.network

import kz.beksultan.test.testapp.network.Model.GamesResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {

    @GET("top")
    suspend fun getGamesList(
        @Header("accept") accept:String,
        @Query("client_id") clientId:String,
        @Query("limit") limit:Int
    ): Response<GamesResponse>

    companion object {
        fun create():Api{
            return Retrofit.Builder()
                .baseUrl("https://api.twitch.tv/kraken/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}