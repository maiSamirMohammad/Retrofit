package com.example.retrofit.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.retrofit.data.model.Photo

class MyPhotosDiffCallback (
    private val  oldList: List<Photo>,
    private val  newList: List<Photo>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id== newList[newItemPosition]. id

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val  oldAlbum = oldList[oldItemPosition]
        val  newAlbum = newList[newItemPosition]
        return when{
            oldAlbum.id != newAlbum.id -> false
            oldAlbum.albumId != newAlbum.albumId -> false
            oldAlbum.title != newAlbum.title -> false
            oldAlbum.url != newAlbum.url -> false
            oldAlbum.thumbnailUrl != newAlbum.thumbnailUrl -> false
            else -> true

        }
    }
}