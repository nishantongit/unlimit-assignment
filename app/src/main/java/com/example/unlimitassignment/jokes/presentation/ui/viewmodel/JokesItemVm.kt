package com.example.unlimitassignment.jokes.presentation.ui.viewmodel

import androidx.databinding.ObservableField
import com.example.unlimitassignment.base.adapter.RecyclerViewAdapter
import com.example.unlimitassignment.jokes.data.entities.local.JokeDom
import com.example.unlimitassignment.jokes.presentation.util.DateTimeUtil

class JokesItemVm(data: JokeDom) : RecyclerViewAdapter.RecyclerViewItemViewModel {

    val joke = ObservableField("")
    val date = ObservableField("")

    init {
        joke.set(data.joke)
        date.set(DateTimeUtil.getFormatedDate(data.createdAt))
    }



}