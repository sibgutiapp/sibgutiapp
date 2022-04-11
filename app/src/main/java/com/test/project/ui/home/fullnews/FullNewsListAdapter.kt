package com.test.project.ui.home.fullnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.project.databinding.FullNewsFragmentBinding

class FullNewsListAdapter : RecyclerView.Adapter<FullNewsListAdapter.ViewHolder>() {
    class ViewHolder(private val binding: FullNewsFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FullNewsFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 0
    }
}