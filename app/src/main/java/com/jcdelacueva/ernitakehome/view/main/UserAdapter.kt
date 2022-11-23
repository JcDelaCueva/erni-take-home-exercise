package com.jcdelacueva.ernitakehome.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jcdelacueva.ernitakehome.R
import com.jcdelacueva.ernitakehome.data.model.User
import com.jcdelacueva.ernitakehome.databinding.ItemUserBinding

class UserAdapter : ListAdapter<User, UserAdapter.ViewHolder>(diffCallBack) {

    interface OnItemClickListener {
        fun onClick(user: User)
    }

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(ItemUserBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(holder.adapterPosition)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onItemClickListener?.onClick(item)
        }
    }

    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                tvName.text = user.name ?: ""
                Glide.with(itemView).load(user.imageUrl).into(imgAvatar)
            }
        }
    }
}
