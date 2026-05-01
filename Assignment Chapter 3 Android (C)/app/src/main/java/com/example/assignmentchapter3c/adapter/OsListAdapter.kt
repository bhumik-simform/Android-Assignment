package com.example.assignmentchapter3c.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.assignmentchapter3c.R
import com.example.assignmentchapter3c.model.OSModel

class OsListAdapter(
    private val context: Context,
    private val osList: List<OSModel>
): BaseAdapter() {
    override fun getCount() = osList.count()

    override fun getItem(position: Int): Any = osList[position]

    override fun getItemId(position: Int) = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_list_view,parent,false)

        val osImageView  = view.findViewById<ImageView>(R.id.iv_os_icon)
        val nameTextView  = view.findViewById<TextView>(R.id.tv_os_name)
        val yearTextView  = view.findViewById<TextView>(R.id.tv_year)

        val item = osList[position]

        osImageView.setImageResource(item.numbersImageId)
        nameTextView.text = item.title
        yearTextView.text = context.getString(R.string.text_founded_year, item.subtitle)

        return view

    }
}