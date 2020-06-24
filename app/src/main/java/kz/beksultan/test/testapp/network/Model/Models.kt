package kz.beksultan.test.testapp.network.Model

data class GamesResponse(val total:Int,
                        val top:List<Games>)

data class Games(val game:Game,
                 val viewers:Int,
                 val channels:Int)

data class Game(val name:String,
                val id:Long,
                val giantbomb_id:Long,
                val box:Size,
                val logo:Size,
                val localized_name:String,
                val locale:String)

data class Size(val large:String,
                val medium:String,
                val small:String,
                val template:String)

data class GameListResponse<out T>(val error:String?,
                                   val status:Int?,
                                   val message:String?,
                                   val data:T?)

