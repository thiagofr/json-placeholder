package com.thiagofr.jsonplaceholder.presenter.userlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.jsonplaceholder.databinding.UserItemBinding

class UserListAdapter: RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val newsViewBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return NewsViewHolder(newsViewBinding)
//    }

    override fun getItemCount() = 10

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }
}