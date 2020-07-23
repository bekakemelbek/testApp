package kz.beksultan.test.testapp.network.repositories

import kz.beksultan.test.testapp.network.Api
import kz.beksultan.test.testapp.network.Model.GamesResponse
import retrofit2.Response

class MoviesRepo {
    suspend fun getList(api_key:String) : Response<GamesResponse> {
        return Api.create().getGamesList(api_key)
    }
}