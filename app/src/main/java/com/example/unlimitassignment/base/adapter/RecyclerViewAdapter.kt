package com.example.unlimitassignment.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    protected var list = ArrayList<RecyclerViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): RecyclerViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            view,
            parent,
            false
        )
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        bind(holder.viewBinding, getViewModel(position), position)
        holder.viewBinding.executePendingBindings()
    }

    private fun bind(
        viewDataBinding: ViewDataBinding,
        viewModel: RecyclerViewItemViewModel,
        position: Int
    ) {
        viewDataBinding.setVariable(BR.vm, viewModel)
    }

    class RecyclerViewHolder(val viewBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewBinding.root)



    abstract fun getViewModel(position: Int): RecyclerViewItemViewModel

    override fun getItemCount(): Int {
        return list.size
    }

    data class RecyclerViewItem(var viewType: String, var any: Any?)

    interface RecyclerViewItemViewModel
}