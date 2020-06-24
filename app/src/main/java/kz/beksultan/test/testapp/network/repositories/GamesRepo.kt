package kz.beksultan.test.testapp.network.repositories

import kz.beksultan.test.testapp.network.Api
import kz.beksultan.test.testapp.network.Model.GamesResponse
import retrofit2.Response

class GamesRepo {
    suspend fun getList(accept:String,clientId:String,limit:Int) : Response<GamesResponse> {
        return Api.create().getGamesList(accept,clientId,limit)
    }
}