package com.example.assignmentchapter3c.decor

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HeroRecyclerViewItemSpacing : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val lastChild = (parent.adapter?.itemCount ?: 0) - 1
        val position = parent.getChildAdapterPosition(view)

        outRect.left = 16
        outRect.right = 16
        outRect.top = 16

        if (position == lastChild) {
            outRect.bottom = 16
        }
    }
}
