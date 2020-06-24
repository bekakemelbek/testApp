package kz.beksultan.test.testapp.ui.listeners

import kz.beksultan.test.testapp.util.room.GameTable

interface LoadMore {
    fun loadMore(list:MutableList<GameTable>)
}