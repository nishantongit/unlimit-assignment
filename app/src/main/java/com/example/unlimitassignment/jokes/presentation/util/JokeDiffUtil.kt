package com.example.unlimitassignment.jokes.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.example.unlimitassignment.jokes.data.entities.local.JokeDom

class JokeDiffUtil(private val oldList: List<JokeDom>, private val newList: List<JokeDom>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when (oldList[oldItemPosition].id) {
            newList[newItemPosition].id -> true
            else -> false
        }
    }

}