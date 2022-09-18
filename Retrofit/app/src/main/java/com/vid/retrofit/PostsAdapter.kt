package com.vid.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vid.retrofit.databinding.PostItemBinding

class PostsAdapter(var postList: ArrayList<Posts>):
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    inner class PostViewHolder(val adapterBinding: PostItemBinding) :
        RecyclerView.ViewHolder(adapterBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.adapterBinding.tvUserId.text = postList[position].userId.toString()
        holder.adapterBinding.tvId.text = postList[position].id.toString()
        holder.adapterBinding.tvTitle.text = postList[position].title
        holder.adapterBinding.tvBody.text = postList[position].subtitle
    }

    override fun getItemCount(): Int {
        return  postList.size
    }

}