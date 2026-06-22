package com.example.assignmentchapter4android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentchapter4android.R
import com.example.assignmentchapter4android.model.UserModel
import com.example.assignmentchapter4android.databinding.ItemUserBinding

class UserListAdapter: ListAdapter<UserModel, UserListAdapter.UserViewHolder>(UserDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
       val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val user = getItem(position)
        holder.bindData(user)
    }

    class UserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(user: UserModel) {
            binding.tvUserName.apply {
                this.text = this.context.getString(R.string.item_user_full_name, user.firstName, user.lastName)
            }

            binding.tvUserName.text = user.email
        }
    }

    class UserDiffCallback: DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(
            oldItem: UserModel,
            newItem: UserModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: UserModel,
            newItem: UserModel
        ) = oldItem == newItem

    }

}

