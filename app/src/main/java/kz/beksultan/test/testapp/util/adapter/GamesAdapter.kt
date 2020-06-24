package kz.beksultan.test.testapp.util.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.game_item.view.*
import kotlinx.android.synthetic.main.loading_item.view.*
import kz.beksultan.test.testapp.R
import kz.beksultan.test.testapp.ui.listeners.LoadMore
import kz.beksultan.test.testapp.util.room.GameTable

class GamesAdapter(recyclerView: RecyclerView,internal var items:MutableList<GameTable?>,internal var context:Context,internal var  list:MutableList<GameTable>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var VIEW_TYPE_ITEM = 0
    var VIEW_TYPE_LOADING = 1
    internal var loadMore: LoadMore? = null
    var isLoading = false
    var visibleThreshold = 10
    var lastVisibleItem = 0
    var totalItemCount = 0
    init {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                totalItemCount = linearLayoutManager.itemCount
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                if(!isLoading && totalItemCount <= (lastVisibleItem+visibleThreshold) ){

                    if(loadMore != null) {
                        loadMore!!.loadMore(list)

                    }
                    isLoading = true
                }
            }
        })
    }

    override fun getItemViewType(position: Int): Int {
        return if(items.get(position) == null){
            VIEW_TYPE_LOADING
        }else{
            VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_LOADING){
            LoadingViewHolder(LayoutInflater.from(context).inflate(R.layout.loading_item, parent, false))
        }else {
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.game_item, parent, false))
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setLoaded(){
        isLoading = false
    }

    fun setLoadMore(loadMore: LoadMore){
        this.loadMore = loadMore
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.channels.text = items.get(position)!!.channels.toString()
            holder.viewers.text = items.get(position)!!.viewers.toString()
            holder.name.text = items.get(position)!!.name.toString()
            Glide.with(context).load(items[position]!!.image).into(holder.image)
        }else if(holder is LoadingViewHolder){
            holder.progressBar.isIndeterminate = true
        }
    }
}



class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val viewers = view.viewersTxt
    val channels = view.channelsTxt
    val image = view.image
    val name = view.nameTxt
    val item = view
}

class LoadingViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val progressBar = view.progressBar
}