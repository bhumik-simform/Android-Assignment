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

class OsRecyclerAdapter(
    private val context: Context,
    private val osList: List<OSModel>,
    private val onItemClick: (View) -> Unit
) :
    RecyclerView.Adapter<OsRecyclerAdapter.OsViewHolder>() {

    inner class OsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val iconImageView = view.findViewById<ImageView>(R.id.iv_rv_os_icon)
        private val nameTextView = view.findViewById<TextView>(R.id.tv_rv_os_name)
        private val yearTextView = view.findViewById<TextView>(R.id.tv_rv_year)

        fun bindData(item: OSModel) {
            iconImageView.setImageResource(item.numbersImageId)
            nameTextView.text = item.title
            yearTextView.text = context.getString(R.string.text_founded_year,item.subtitle)

            view.setOnClickListener {
                onItemClick(view)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_recycler_view, parent, false
        )
        return OsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: OsViewHolder,
        position: Int
    ) {
        val item = osList.elementAt(position)
        holder.bindData(item)
    }

    override fun getItemCount() = osList.count()
}