package com.example.assignmentchapter3c.decor

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemSpacingDecor(private val spanCount: Int, private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        val row = position / spanCount
        val lastRow = (state.itemCount - 1) / spanCount

        outRect.left = spacing - (column * spacing / spanCount)
        outRect.right = (column+1) * spacing / spanCount

        outRect.top = spacing

        when(row) {
            0->outRect.top = 16
            lastRow->outRect.bottom = 16
        }
    }
}
