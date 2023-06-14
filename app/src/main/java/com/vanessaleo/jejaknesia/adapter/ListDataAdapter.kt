package com.vanessaleo.jejaknesia.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.databinding.ItemDataBinding
import com.vanessaleo.jejaknesia.response.DataItemItem


class ListDataAdapter : RecyclerView.Adapter<ListDataAdapter.ListViewHolder>() {
    private val list = ArrayList<DataItemItem>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(data: ArrayList<DataItemItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private var binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItemItem) {
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(data)
            }

            binding.apply {
                tvPlaceName.text = data.placeName
                tvPlaceCity.text = data.city
                tvPlaceDesc.text = data.descPlace

                Glide.with(itemView)
                    .load(data.imageUrl)
                    .placeholder(R.color.grey)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivDataplace)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItemItem)
    }
}
