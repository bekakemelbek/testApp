package kz.beksultan.test.testapp.util.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.loading_item.view.*
import kz.beksultan.test.testapp.R
import kz.beksultan.test.testapp.network.Model.ListMovie
import kz.beksultan.test.testapp.ui.DetailActivity
import kz.beksultan.test.testapp.ui.listeners.LoadMore

class GamesAdapter(recyclerView: RecyclerView,internal var items:MutableList<ListMovie?>,internal var context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var VIEW_TYPE_ITEM = 0
    var VIEW_TYPE_LOADING = 1
    internal var loadMore: LoadMore? = null
    var isLoading = false
    var visibleThreshold = 2
    var lastVisibleItem = 0
    var totalItemCount = 0
    val IMAGE_PATH = "https://image.tmdb.org/t/p/w500/"
    init {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                totalItemCount = linearLayoutManager.itemCount
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                if (!isLoading && totalItemCount <= (lastVisibleItem+visibleThreshold)){
                    if (loadMore != null) {
                        Log.e("ASD","KIRDIM")
                        loadMore!!.loadMore()
                    }
                    isLoading = true
                }
            }
        })
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.get(position) == null){
            VIEW_TYPE_LOADING
        }else {
            VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LOADING){
            LoadingViewHolder(LayoutInflater.from(context).inflate(R.layout.loading_item, parent, false))
        }else {
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
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

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val name = items.get(position)!!.name
            val rating = items.get(position)!!.rating
            val date = items.get(position)!!.date
            val description = items.get(position)!!.description
            val image = items.get(position)!!.poster
            holder.name.text = name
            holder.rating.text = rating
            holder.date.text = date
            holder.image.clipToOutline = true
            Glide.with(context).load(IMAGE_PATH+items[position]!!.poster).into(holder.image)

            holder.item.setOnClickListener {
                Intent(context,DetailActivity::class.java).also {
                    it.putExtra("name",name)
                    it.putExtra("poster",image)
                    it.putExtra("rating",rating)
                    it.putExtra("description",description)
                    it.putExtra("date",date)
                    context.startActivity(it)
                }
            }
        }else if(holder is LoadingViewHolder){
            holder.progressBar.isIndeterminate = true
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val date = view.dateTxt
    val rating = view.rateTxt
    val image = view.image
    val name = view.nameTxt
    val item = view
}

class LoadingViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val progressBar = view.progressBar
}