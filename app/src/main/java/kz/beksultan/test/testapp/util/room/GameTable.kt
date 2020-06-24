package kz.beksultan.test.testapp.util.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GameTable")
data class GameTable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int = 0,
    @ColumnInfo(name="name")
    var name: String = "",
    @ColumnInfo(name="image")
    var image: String = "",
    @ColumnInfo(name="viewers")
    var viewers: Int = 0,
    @ColumnInfo(name="channels")
    var channels: Int = 0
)

