package com.example.unlimitassignment.jokes.presentation.ui.viewmodel

import android.text.format.DateFormat
import androidx.databinding.ObservableField
import com.example.unlimitassignment.base.adapter.RecyclerViewAdapter
import com.example.unlimitassignment.jokes.data.entities.local.JokeDom
import java.util.*

class JokesItemVm(data: JokeDom) : RecyclerViewAdapter.RecyclerViewItemViewModel {

    val joke = ObservableField("")
    val date = ObservableField("")


    init {
        joke.set(data.joke)
        date.set(getFormatedDate(data.createdAt))
    }


    private fun getFormatedDate(timestamp:Long): String {
        val cal: Calendar = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = timestamp
        return DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString()
    }

}