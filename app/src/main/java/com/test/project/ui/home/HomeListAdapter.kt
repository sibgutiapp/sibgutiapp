package com.test.project.ui.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.project.databinding.ItemHomeListBinding
import com.test.project.databinding.NewsCardviewBinding
import com.test.project.domain.entit.News


class HomeListAdapter :
    RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {
    private val dataSet: MutableList<News> = mutableListOf()

    class ViewHolder(val binding: NewsCardviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NewsCardviewBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.titleTextveiw.text = dataSet[position].author.fullName
        viewHolder.binding.authorTextview.text = dataSet[position].title
        viewHolder.binding.dateTextview.text = dataSet[position].dateTime
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun addNews(dataset : List<News>){
        this.dataSet.clear()
        this.dataSet.addAll(dataset)
        notifyDataSetChanged()
    }
}