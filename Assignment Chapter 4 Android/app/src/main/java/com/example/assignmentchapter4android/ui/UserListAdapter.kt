package com.example.assignmentchapter4android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentchapter4android.R
import com.example.assignmentchapter4android.model.User
import com.example.assignmentchapter4android.databinding.ItemUserBinding
import com.squareup.picasso.Picasso
import com.squareup.picasso.Callback
import java.lang.Exception

class UserListAdapter : ListAdapter<User, UserListAdapter.UserViewHolder>(UserDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val user = getItem(position)
        holder.bindData(user)
    }

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(user: User) {

            Picasso.get()
                .load(user.imageUrl)
                .error(R.drawable.ic_error_image)
                .into(binding.ivUserAvatar, object : Callback {
                    override fun onSuccess() {
                        binding.progressCircular.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.progressCircular.visibility = View.GONE
                    }
                })

            binding.tvUserName.apply {
                this.text = this.context.getString(
                    R.string.item_user_full_name,
                    user.firstName,
                    user.lastName
                )
            }

            binding.tvUserEmail.text = user.email
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ) = oldItem == newItem

    }

}

