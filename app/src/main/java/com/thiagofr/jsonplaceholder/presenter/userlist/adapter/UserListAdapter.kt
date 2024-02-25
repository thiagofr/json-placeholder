package com.thiagofr.jsonplaceholder.presenter.userlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.jsonplaceholder.databinding.UserItemBinding
import com.thiagofr.jsonplaceholder.model.UserUI

class UserListAdapter(private val onItemClick: (UserUI) -> Unit) : RecyclerView.Adapter<UserViewHolder>() {

    private val userList = mutableListOf<UserUI>()
    private val currentList = mutableListOf<UserUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, onItemClick)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun filter(term: String?) {
        if (term?.isNotEmpty() == true) {
            currentList.clear()
            val newList =
                userList.filter { it.name.lowercase().contains(term.lowercase()) }
            currentList.addAll(newList)
            notifyDataSetChanged()
        } else {
            resetFilter()
        }
    }

    fun resetFilter() {
        currentList.clear()
        currentList.addAll(userList)
        notifyDataSetChanged()
    }

    fun addList(userList: List<UserUI>) {
        this.userList.clear()
        this.userList.addAll(userList)
        currentList.addAll(this.userList)
        notifyDataSetChanged()
    }
}