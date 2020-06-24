package kz.beksultan.test.testapp.ui.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.beksultan.test.testapp.network.repositories.GamesRepo
import kz.beksultan.test.testapp.ui.MainActivity.Companion.db
import kz.beksultan.test.testapp.ui.listeners.MainListener
import kz.beksultan.test.testapp.util.room.GameTable

class MainViewModel:ViewModel() {

    var listener:MainListener? = null

    fun loadData() {
        listener!!.onProgress()
        CoroutineScope(Dispatchers.IO).launch {
            if (db!!.gameDao().getAll().size == 0){
                val responseOrder = GamesRepo().getList("application/vnd.twitchtv.v5+json","sd4grh0omdj9a31exnpikhrmsu3v46",100)
                val bodyOrder = responseOrder.body()
                Log.e("RESPONSE",responseOrder.toString())
                Log.e("RESPONSEORDER",bodyOrder.toString())
                if(responseOrder.isSuccessful){
                    if (bodyOrder != null) {
                        bodyOrder.top.forEach {
                            val entity = GameTable()
                            entity.id = 0
                            entity.channels = it.channels
                            entity.viewers = it.viewers
                            entity.name = it.game.name
                            entity.image = it.game.box.large
                            db!!.gameDao().insert(entity)
                        }
                        val list = db!!.gameDao().getAll()
                        withContext(Dispatchers.Main) {
                            listener!!.onLoaded(list)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            listener!!.onError()
                        }
                    }
                }else{
                    withContext(Dispatchers.Main) {
                        listener!!.onError()
                    }
                }
            }else {
                val list = db!!.gameDao().getAll()
                withContext(Dispatchers.Main) {
                    listener!!.onLoaded(list)
                }
            }

        }
    }
    fun reviewClicked(view: View){
        listener!!.reviewClicked()
    }

}