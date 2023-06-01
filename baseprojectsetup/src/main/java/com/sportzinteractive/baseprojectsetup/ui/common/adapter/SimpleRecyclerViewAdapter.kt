package com.sportzinteractive.baseprojectsetup.ui.common.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sportzinteractive.baseprojectsetup.ui.common.Inflate

class SimpleRecyclerViewAdapter<VB: ViewBinding,Data:Any>(
    private val inflate: Inflate<VB>,
    private val onBind: (position:Int,VB,Data) -> Unit,
    private val itemInit: ((SimpleRecyclerViewAdapter<VB, Data>.SimpleViewHolder) -> Unit)? = null,
    private val itemCountToFlex: Int? = null
) : RecyclerView.Adapter<SimpleRecyclerViewAdapter<VB, Data>.SimpleViewHolder>() {

    private var recyclerView: RecyclerView? = null

    private var _list = ArrayList<Data>()

    val list: ArrayList<Data>
        get() = _list

    fun setData(list: List<Data>) {
        this._list.clear()
        this._list = ArrayList(list)
        notifyItemRangeInserted(0, list.size)
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun clear(notify: Boolean) {
        this._list.clear()
        if (notify)
            notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun reset(list: List<Data>) {
        clear(false)
        this._list.addAll(list)
        notifyDataSetChanged()
    }




    inner class SimpleViewHolder(private val binding: VB) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemInit?.invoke(this)
        }

        fun getBinding(): VB {
            return binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int
    ): SimpleViewHolder {
        val binding = inflate.invoke(LayoutInflater.from(parent.context),parent,false)
//        safeLet(recyclerView?.measuredWidth, itemCountToFlex) { totalWidth, count ->
//            binding.root.setWidth(totalWidth / count)
//        }
        return SimpleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        _list.getOrNull(position)?.let {
            onBind.invoke(position,holder.getBinding(),it)
        }
    }

    override fun getItemCount(): Int {
        return _list.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }
}

fun <VB : ViewDataBinding, Data : Any> RecyclerView.setUpRecyclerViewAdapter(
    inflate: Inflate<VB>,
    onBindCallback: (position: Int, VB, Data) -> Unit,
    itemInitCallback: ((SimpleRecyclerViewAdapter<VB, Data>.SimpleViewHolder) -> Unit)? = null,
    itemCountToFlex: Int? = null
): SimpleRecyclerViewAdapter<VB, Data> {
    val simpleAdapter =
        SimpleRecyclerViewAdapter(inflate, onBindCallback, itemInitCallback, itemCountToFlex)
    adapter = simpleAdapter
    return simpleAdapter
}
