package com.sportzinteractive.baseprojectsetup.ui.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sportzinteractive.baseprojectsetup.ui.common.Inflate
import com.sportzinteractive.baseprojectsetup.utils.safeLet
import com.sportzinteractive.baseprojectsetup.utils.setWidth


class SingleSelectionAdapter<Data, VB : ViewBinding>(
    private val inflate: Inflate<VB>,
    private val onBind: (itemBinding: VB, data: Data, isSelected: Boolean) -> Unit,
    private val onItemSelectListener: ((data: Data) -> Unit)? = null,
    private val itemCountToFlex: Int? = null
) : RecyclerView.Adapter<SingleSelectionAdapter<Data, VB>.SingleSelectionViewHolder>() {

    private var _recycler: RecyclerView? = null

    private val _selectedItem = MutableLiveData<Data?>()

    inner class SingleSelectionViewHolder(private val binding: VB) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                _list.getOrNull(bindingAdapterPosition)?.let { data ->
                    //setSelectedPosition(bindingAdapterPosition)
                    onItemSelectListener?.invoke(data)
                }
            }
        }

        fun getBinding(): VB {
            return binding
        }
    }

    private var _list = ArrayList<Data>()

    val list: List<Data>
        get() = _list

    private var _selectedPosition: Int? = null

    val selectedPosition: Int
        get() = _selectedPosition ?: -1

    fun setSelectedPosition(
        position: Int,
        animate: Boolean = true
    ) {
        if (0 <= position && position < _list.size) {
            if (_selectedPosition == position) return
            _selectedPosition?.let {
                if (it != -1) {
                    notifyItemChanged(it)
                }
            }
            _selectedPosition = position
            _selectedPosition?.let {
                if (it != -1) {
                    notifyItemChanged(it)
                }
            }
            _selectedItem.value = _list.getOrNull(position)
            _recycler?.safeSmoothScrollToPosition(position)
        }
    }

    fun setData(list: List<Data>) {
        _list.clear()
        _list.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleSelectionViewHolder {
        val binding = inflate.invoke(LayoutInflater.from(parent.context), parent, false)
        safeLet(_recycler?.measuredWidth, itemCountToFlex) { totalWidth, count ->
            binding.root.setWidth(totalWidth / count)
        }
        return SingleSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SingleSelectionViewHolder, position: Int) {
        _list.getOrNull(position)?.let {
            onBind.invoke(holder.getBinding(), it, position == _selectedPosition)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this._recycler = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this._recycler = null
    }

    override fun getItemCount(): Int {
        return _list.size
    }

}

fun RecyclerView.safeSmoothScrollToPosition(position: Int) {
    try {
        if (0 <= position && position < adapter?.itemCount ?: 0) {
            smoothScrollToPosition(position)
        }
    } catch (e: Exception) {
        //just to be sure that crash wont happen
        e.printStackTrace()
    }
}