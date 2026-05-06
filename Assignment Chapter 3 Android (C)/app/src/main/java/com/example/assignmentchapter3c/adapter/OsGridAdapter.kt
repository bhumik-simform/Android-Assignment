package com.example.assignmentchapter3c.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentchapter3c.R
import com.example.assignmentchapter3c.model.OSModel

class OsGridAdapter(private val context: Context, private val osList: List<OSModel>):
RecyclerView.Adapter<OsGridAdapter.OSGridViewHolder>(){

    inner class OSGridViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val iconImageView: ImageView = view.findViewById(R.id.iv_gv_icon)
        val nameTextView: TextView = view.findViewById(R.id.tv_gv_os_name)
        val yearTextView: TextView = view.findViewById(R.id.tv_gv_year)
        fun bindData(item: OSModel) {
            iconImageView.setImageResource(item.numbersImageId)
            nameTextView.text = item.title
            yearTextView.text = context.getString(R.string.text_founded_year,item.subtitle)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OSGridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_view,parent,false)
        return OSGridViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: OSGridViewHolder,
        position: Int
    ) {
        val item = osList.elementAt(position)
        holder.bindData(item)
    }

    override fun getItemCount() = osList.count()


}