package kz.beksultan.test.testapp.util.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {

    @Query("SELECT * FROM GameTable")
    fun getAll(): MutableList<GameTable>

    @Insert
    fun insert(sale: GameTable)
}