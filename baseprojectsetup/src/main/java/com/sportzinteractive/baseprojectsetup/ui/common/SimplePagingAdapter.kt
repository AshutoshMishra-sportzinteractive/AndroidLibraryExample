package com.sportzinteractive.baseprojectsetup.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class SimplePagingAdapter<VB : ViewBinding, Data : Any>(
    private val inflate: Inflate<VB>,
    itemComparator: DiffUtil.ItemCallback<Data>,
    private val onBind: (position: Int, VB, Data) -> Unit,
    private val onChangePayload: ((position: Int, VB, Bundle) -> Unit)? = null,
    private val itemInit: ((SimplePagingAdapter<VB, Data>.SimpleViewHolder) -> Unit)? = null
) : PagingDataAdapter<Data, SimplePagingAdapter<VB, Data>.SimpleViewHolder>(itemComparator) {

    inner class SimpleViewHolder(private val binding: VB) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemInit?.invoke(this)
        }

        fun getBinding(): VB {
            return binding
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimplePagingAdapter<VB, Data>.SimpleViewHolder {
        val binding = inflate.invoke(LayoutInflater.from(parent.context), parent, false)
        return SimpleViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SimplePagingAdapter<VB, Data>.SimpleViewHolder,
        position: Int
    ) {
        getItem(position)?.let { data ->
            onBind.invoke(position, holder.getBinding(), data)
        }
    }

    override fun onBindViewHolder(
        holder: SimpleViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val data = getItem(position)
        if(payloads.isEmpty() || payloads[0] !is Bundle) {
            data ?: return
            onBind.invoke(position, holder.getBinding(), data)
        }
        else {
            val bundle = payloads[0] as Bundle
            onChangePayload?.invoke(position, holder.getBinding(), bundle)
        }
    }
}