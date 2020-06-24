package kz.beksultan.test.testapp.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kz.beksultan.test.testapp.R
import kz.beksultan.test.testapp.databinding.ActivityMainBinding
import kz.beksultan.test.testapp.ui.listeners.LoadMore
import kz.beksultan.test.testapp.ui.listeners.MainListener
import kz.beksultan.test.testapp.ui.viewmodels.MainViewModel
import kz.beksultan.test.testapp.util.adapter.GamesAdapter
import kz.beksultan.test.testapp.util.indeterminateProgressDialog
import kz.beksultan.test.testapp.util.room.AppDatabase
import kz.beksultan.test.testapp.util.room.GameTable

class MainActivity : AppCompatActivity(),MainListener,LoadMore {
    private var progressBar: ProgressDialog? = null
    private var items:MutableList<GameTable?> = ArrayList()
    private var count = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewmodel = viewModel

        if (db == null) db = Room.databaseBuilder(this, AppDatabase::class.java, "database").build()

        viewModel.listener = this
        viewModel.loadData()
    }

    companion object {
        var db: AppDatabase? = null
        var adapter:GamesAdapter? = null
    }

    override fun onError() {
        progressBar?.dismiss()
        Toast.makeText(this,"Ошибка загрузки",Toast.LENGTH_LONG).show()
    }

    override fun onProgress() {
        progressBar = indeterminateProgressDialog(R.string.processing_3xdot)
        progressBar!!.show()
    }

    override fun onLoaded(list:MutableList<GameTable>) {
        progressBar?.dismiss()
        Log.e("ASD",list.toString())
        if(gamesRv.adapter == null) {
            gamesRv.layoutManager = LinearLayoutManager(this)
            items.addAll(list.take(10).toMutableList())
            adapter = GamesAdapter(gamesRv,items,this,list)

            gamesRv.adapter = adapter
            adapter!!.setLoadMore(this)
        }
    }

    override fun reviewClicked() {
        val intent = Intent(this,ReviewActivity::class.java)
        startActivity(intent)
    }

    override fun loadMore(list:MutableList<GameTable>) {
        if(count+10 < list.size){
            items.add(null)
            adapter!!.notifyItemInserted(items.size)

            Handler().postDelayed({
                Log.e("ASD3",count.toString())
                items.removeAt(items.size-1)
                adapter!!.notifyItemRemoved(items.size)
                count += 10
                items.clear()
                items.addAll(list.take(count))
                adapter!!.notifyDataSetChanged()
                adapter!!.setLoaded()
            },2000)
        }
    }


}

