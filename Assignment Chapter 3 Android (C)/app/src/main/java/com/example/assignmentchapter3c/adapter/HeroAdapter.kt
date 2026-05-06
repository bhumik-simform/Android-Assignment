package com.example.assignmentchapter3c.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.example.assignmentchapter3c.R
import com.example.assignmentchapter3c.model.HeroModel
import com.squareup.picasso.Picasso

class HeroAdapter(
    private val context: Context,
    private val heroList: List<HeroModel>
) : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {


    inner class HeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val heroImageView: ImageView = view.findViewById(R.id.iv_hero)
        val heroNameTextView: TextView = view.findViewById(R.id.tv_hero_name)
        val heroRealNameTextView: TextView = view.findViewById(R.id.tv_real_name)
        val teamTextView: TextView = view.findViewById(R.id.tv_team)
        val firstAppearanceTextView: TextView = view.findViewById(R.id.tv_first_appearance)
        val createdByTextView: TextView = view.findViewById(R.id.tv_created_by)
        val publisherTextView: TextView = view.findViewById(R.id.tv_publisher)
        val bioTextView: TextView = view.findViewById(R.id.tv_bio)

        val expandedView: View = view.findViewById(R.id.expanded_layout)

        fun bindData(item: HeroModel) {
            Picasso.get().load(item.imageURL).into(heroImageView)
            heroNameTextView.text = item.name
            heroRealNameTextView.text = item.realName

            teamTextView.text = context.getString(R.string.text_team, item.team)
            firstAppearanceTextView.text = context.getString(R.string.text_first_appearance, item.firstAppearance)
            createdByTextView.text = context.getString(R.string.text_created_by, item.createdBy)
            publisherTextView.text = context.getString(R.string.text_publisher, item.publisher)
            bioTextView.text = context.getString(R.string.text_bio, item.bio)
            expandedView.visibility = if(item.isExpanded) View.VISIBLE else View.GONE
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val item = heroList.elementAt(position)
        holder.bindData(item)
        holder.itemView.setOnClickListener {
            item.isExpanded = !item.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = heroList.count()


}