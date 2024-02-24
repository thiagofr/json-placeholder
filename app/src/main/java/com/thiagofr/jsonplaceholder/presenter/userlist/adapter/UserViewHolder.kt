package com.thiagofr.jsonplaceholder.presenter.userlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.jsonplaceholder.databinding.UserItemBinding
import com.thiagofr.jsonplaceholder.model.UserUI

class UserViewHolder(
    private val binding: UserItemBinding,
    private val onItemClick: (UserUI) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userUI: UserUI) {
        binding.tvName.text = userUI.name
        binding.tvEmail.text = userUI.email
        binding.root.setOnClickListener {
            onItemClick(userUI)
        }
    }
}