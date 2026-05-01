package com.example.assignmentchapter3c.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.ConstraintSetx
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentchapter3c.R

class listAdapter(context: Context, private val numberLists: List<NumberView>): ArrayAdapter<NumberView>(context,0,numberLists) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView
        val holder: RecyclerView.ViewHolder

        if(itemView==null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_list_view,parent,false)

            holder = RecyclerView.ViewHolder(itemView)
        }
    }
}