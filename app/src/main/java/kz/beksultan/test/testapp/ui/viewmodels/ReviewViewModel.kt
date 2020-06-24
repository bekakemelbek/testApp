package kz.beksultan.test.testapp.ui.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import kz.beksultan.test.testapp.ui.listeners.ReviewListener

class ReviewViewModel:ViewModel() {

    var listener:ReviewListener? = null

    fun sendClicked(view: View){
        listener!!.sendClicked()
    }

    fun backClicked(view: View){
        listener!!.backClicked()
    }
}