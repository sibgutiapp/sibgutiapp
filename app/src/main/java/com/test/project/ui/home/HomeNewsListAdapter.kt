package com.test.project.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.project.databinding.ItemHomeNewsListBinding
import com.test.project.domain.entity.News


class HomeNewsListAdapter :
    RecyclerView.Adapter<HomeNewsListAdapter.ViewHolder>() {

    var dataList: List<News>? = null

    fun setUpdatedData(dataList: List<News>) {
        this.dataList = dataList
    }

    class ViewHolder(val binding: ItemHomeNewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: News) {
            with(binding) {
                textviewDescription.text = data.description
                textviewTitle.text = data.title
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeNewsListBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if (dataList == null) return 0
        return dataList!!.size
    }
}