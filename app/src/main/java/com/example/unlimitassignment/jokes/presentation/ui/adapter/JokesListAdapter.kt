package com.example.unlimitassignment.jokes.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.unlimitassignment.R
import com.example.unlimitassignment.base.adapter.RecyclerViewAdapter
import com.example.unlimitassignment.jokes.data.entities.local.JokeDom
import com.example.unlimitassignment.jokes.presentation.ui.viewmodel.JokesItemVm
import com.example.unlimitassignment.jokes.presentation.util.JokeDiffUtil


class JokesListAdapter : RecyclerViewAdapter() {

    private var dataList = ArrayList<JokeDom>()

    override fun getViewModel(position: Int): RecyclerViewItemViewModel {
        return JokesItemVm(
            list[position].any as JokeDom
        )
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.joke_item
    }

    fun swapData(dataDoms: ArrayList<JokeDom>) {
        val diffResult = DiffUtil.calculateDiff(JokeDiffUtil(oldList = dataList, newList = dataDoms))
        list.clear()
        dataDoms.forEach {
            list.add(RecyclerViewItem("", it))
        }
        dataList = dataDoms
        diffResult.dispatchUpdatesTo(this)
    }


}