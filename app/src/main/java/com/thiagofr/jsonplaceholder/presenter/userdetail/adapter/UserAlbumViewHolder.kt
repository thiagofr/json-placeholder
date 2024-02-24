package com.thiagofr.jsonplaceholder.presenter.userdetail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.jsonplaceholder.databinding.AlbumsItemBinding
import com.thiagofr.jsonplaceholder.model.AlbumUI

class UserAlbumViewHolder(
    private val binding: AlbumsItemBinding,
    private val onItemClick: (AlbumUI) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userUI: AlbumUI) {
        binding.tvName.text = userUI.title
        binding.cvAlbum.setOnClickListener {
            onItemClick(userUI)
        }
    }
}