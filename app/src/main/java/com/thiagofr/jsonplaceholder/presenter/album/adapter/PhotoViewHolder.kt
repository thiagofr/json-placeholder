package com.thiagofr.jsonplaceholder.presenter.album.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.thiagofr.jsonplaceholder.R
import com.thiagofr.jsonplaceholder.databinding.PhotoItemBinding
import com.thiagofr.jsonplaceholder.model.PhotoUI

class PhotoViewHolder(
    private val binding: PhotoItemBinding,
    private val onItemClick: (PhotoUI) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photoUI: PhotoUI) {
        with(binding) {
            tvTitle.text = photoUI.title
            getImage(photoUI.thumbnailUrl)
            cvAlbum.setOnClickListener {
                onItemClick.invoke(photoUI)
            }
        }
    }

    private fun getImage(url: String) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_image)
            .into(binding.imgPhoto)
    }
}