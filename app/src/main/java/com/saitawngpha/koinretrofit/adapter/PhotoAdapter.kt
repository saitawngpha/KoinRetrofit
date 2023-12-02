package com.saitawngpha.koinretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.saitawngpha.koinretrofit.R
import com.saitawngpha.koinretrofit.databinding.PhotoRowBinding
import com.saitawngpha.koinretrofit.response.ResponsePhoto

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 02/12/2023.
 */
class PhotoAdapter: RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    private lateinit var binding: PhotoRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = PhotoRowBinding.inflate(inflater)
        return ViewHolder()
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class ViewHolder(): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponsePhoto.Hit){
            binding.apply {
                tvUserName.text = item.user
                tvCommentCount.text = item.comments.toString()
                tvLikeCount.text = item.likes.toString()
                imgArt.load(item.previewURL) {
                    crossfade(true)
                    scale(Scale.FILL)
                    placeholder(R.drawable.placeholder)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<ResponsePhoto.Hit>() {
        override fun areItemsTheSame(
            oldItem: ResponsePhoto.Hit,
            newItem: ResponsePhoto.Hit
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponsePhoto.Hit,
            newItem: ResponsePhoto.Hit
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}