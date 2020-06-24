package kz.beksultan.test.testapp.util.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(GameTable::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}