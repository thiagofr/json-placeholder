package com.thiagofr.jsonplaceholder.presenter.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.jsonplaceholder.databinding.PhotoItemBinding
import com.thiagofr.jsonplaceholder.model.PhotoUI

class PhotoAdapter(
    private val albumList: List<PhotoUI>,
    private val onItemClick: (PhotoUI) -> Unit
) : RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding, onItemClick)
    }

    override fun getItemCount() = albumList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(albumList[position])
    }
}