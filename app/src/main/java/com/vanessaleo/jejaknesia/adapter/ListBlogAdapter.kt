package com.vanessaleo.jejaknesia.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.databinding.ItemBlogBinding
import com.vanessaleo.jejaknesia.model.BlogModel
import com.vanessaleo.jejaknesia.response.BlogResponse
import com.vanessaleo.jejaknesia.response.DataItem
import com.vanessaleo.jejaknesia.ui.blog.BlogViewModel


class ListBlogAdapter : RecyclerView.Adapter<ListBlogAdapter.ListViewHolder>() {
    private val list = ArrayList<DataItem>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListBlog(blogs: ArrayList<DataItem>) {
        list.clear()
        list.addAll(blogs)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private var binding: ItemBlogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: DataItem) {
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(blog)
            }

            binding.apply {

                tvBlogTitle.text = blog.title
                blogContent.text = blog.content
                Glide.with(itemView)
                    .load(blog.photo)
                    .placeholder(R.color.grey)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivBlog)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }
}