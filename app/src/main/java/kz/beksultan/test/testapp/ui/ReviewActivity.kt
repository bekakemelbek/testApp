package kz.beksultan.test.testapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_review.*
import kz.beksultan.test.testapp.R
import kz.beksultan.test.testapp.databinding.ActivityMainBinding
import kz.beksultan.test.testapp.databinding.ActivityReviewBinding
import kz.beksultan.test.testapp.ui.listeners.ReviewListener
import kz.beksultan.test.testapp.ui.viewmodels.MainViewModel
import kz.beksultan.test.testapp.ui.viewmodels.ReviewViewModel

class ReviewActivity : AppCompatActivity(),ReviewListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityReviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_review)
        val viewModel = ViewModelProviders.of(this).get(ReviewViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.listener = this
    }

    override fun sendClicked() {
        reviewEdit.setText("")
        finish()
    }

    override fun backClicked() {
        finish()
    }
}
