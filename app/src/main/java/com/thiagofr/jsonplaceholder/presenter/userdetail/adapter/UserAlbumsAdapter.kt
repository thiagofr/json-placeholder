package com.thiagofr.jsonplaceholder.presenter.userdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.jsonplaceholder.databinding.AlbumsItemBinding
import com.thiagofr.jsonplaceholder.model.AlbumUI

class UserAlbumsAdapter(
    private val albumList: List<AlbumUI>,
    private val onItemClick: (AlbumUI) -> Unit
) : RecyclerView.Adapter<UserAlbumViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAlbumViewHolder {
        val binding = AlbumsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserAlbumViewHolder(binding, onItemClick)
    }

    override fun getItemCount() = albumList.size

    override fun onBindViewHolder(holder: UserAlbumViewHolder, position: Int) {
        holder.bind(albumList[position])
    }
}