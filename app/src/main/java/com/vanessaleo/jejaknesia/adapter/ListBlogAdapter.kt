package com.vanessaleo.jejaknesia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vanessaleo.jejaknesia.databinding.ItemBlogBinding
import com.vanessaleo.jejaknesia.model.Blog
import com.vanessaleo.jejaknesia.ui.DetailBlogActivity

class ListBlogAdapter(private val listBlog: ArrayList<Blog>) : RecyclerView.Adapter<ListBlogAdapter.ListViewHolder>() {
    class ListViewHolder(var binding: ItemBlogBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, author, date, content, photo) = listBlog[position]
        holder.binding.tvBlogTitle.text = title
        holder.binding.blogContent.text = content
        holder.binding.ivBlog.setImageResource(photo)

        holder.itemView.setOnClickListener {
            val intentToDetail = Intent(holder.itemView.context, DetailBlogActivity::class.java)
            intentToDetail.putExtra(DetailBlogActivity.KEY_BLOG, listBlog[holder.adapterPosition])
            holder.itemView.context.startActivity(intentToDetail)
        }
    }

    override fun getItemCount(): Int = listBlog.size
}