package com.thiagofr.jsonplaceholder.presenter.userlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.jsonplaceholder.databinding.UserItemBinding

class UserViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.tvName.text = "Name"
        binding.tvEmail.text = "Email"
    }
}