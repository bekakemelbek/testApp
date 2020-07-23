package kz.beksultan.test.testapp.network.Model

data class MoviesResponse(val created_by:String,
                          val description:String,
                          val favorite_count:Int,
                          val id:Int,
                          val items:List<Movie>,
                          val item_count:Int,
                          val iso_639_1:String,
                          val name:String,
                          val poster_path:String)

data class Movie(val poster_path:String,
                 val popularity:Double,
                 val vote_count:Int,
                 val video:Boolean,
                 val media_type:String,
                 val id:Int,
                 val adult:Boolean,
                 val backdrop_path:String,
                 val original_language:String,
                 val original_title:String,
                 val genre_ids:List<Int>,
                 val title:String,
                 val vote_average:String,
                 val overview:String,
                 val release_date:String)