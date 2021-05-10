package com.youtubeplaylist.viewModel

import androidx.lifecycle.ViewModel
import com.youtubeplaylist.Interface.DataTransfer
import com.youtubeplaylist.MainActivity

class GoogleLogin : ViewModel() {

    lateinit var context:MainActivity
    lateinit var dataTransfer:DataTransfer

    fun showToast()
    {
      //  context.showToastInActivity()
       // Toast.makeText(context,"this is toast message", Toast.LENGTH_SHORT).show()
        println("sanjeev")
        dataTransfer.datasend("sanjeev")
    }

}