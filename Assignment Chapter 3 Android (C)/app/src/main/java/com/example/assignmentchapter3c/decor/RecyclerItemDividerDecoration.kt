package com.example.assignmentchapter3c.decor

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemDividerDecoration: RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val paint = Paint().apply {
            color = Color.BLACK
            strokeWidth = 2f
        }

        val childCount = parent.childCount
        for(i in 0 until childCount-1) {
            val child = parent.getChildAt(i)

            val top = child.bottom.toFloat()
            val left = child.left.toFloat()
            val right = child.right.toFloat()

            c.drawLine(left,top,right,top,paint)
        }
    }

}