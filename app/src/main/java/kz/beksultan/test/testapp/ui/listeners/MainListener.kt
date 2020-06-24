package kz.beksultan.test.testapp.ui.listeners

import kz.beksultan.test.testapp.util.room.GameTable

interface MainListener {
    fun onError()
    fun onProgress()
    fun onLoaded(list:MutableList<GameTable>)
    fun reviewClicked()
}