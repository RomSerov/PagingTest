package com.example.pagingtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingtest.databinding.ItemRickMortyBinding
import com.example.pagingtest.model.RickMorty

class RickMortyPagedAdapter : PagingDataAdapter<RickMorty, RickMortyPagedAdapter.MyViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            textView.text = "${currentItem?.name}"
            Glide.with(holder.itemView).load(currentItem?.image).into(imageView)
            imageView.clipToOutline = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemRickMortyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class MyViewHolder(val binding: ItemRickMortyBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<RickMorty>() {
            override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem == newItem
            }
        }
    }
}