package com.thiagofr.jsonplaceholder.presenter.userlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.jsonplaceholder.databinding.UserItemBinding
import com.thiagofr.jsonplaceholder.model.UserUI

class UserListAdapter: RecyclerView.Adapter<UserViewHolder>() {

    private val userList = mutableListOf<UserUI>()
    private val currentList = mutableListOf<UserUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun addList(userList: List<UserUI>) {
        this.userList.addAll(userList)
        currentList.addAll(this.userList)
        notifyDataSetChanged()
    }
}