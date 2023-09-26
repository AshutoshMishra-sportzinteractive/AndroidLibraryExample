package com.sportzinteractive.baseprojectsetup.ui.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpacingDecoration(private val horizontalOffset: Int, firstItemLeftOffset: Int? = null, lastItemRightOffset: Int? = null): RecyclerView.ItemDecoration() {

    private val mFirstItemLeftOffset = firstItemLeftOffset ?: horizontalOffset
    private val mLastItemRightOffset = lastItemRightOffset ?: horizontalOffset

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        outRect.left = if (position == 0)
            mFirstItemLeftOffset
        else
            horizontalOffset

        if(position == itemCount - 1)
            outRect.right = mLastItemRightOffset
    }
}