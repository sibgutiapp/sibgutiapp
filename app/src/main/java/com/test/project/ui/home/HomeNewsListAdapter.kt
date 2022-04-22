package com.test.project.ui.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.project.databinding.ItemHomeNewsListBinding
import com.test.project.domain.entity.News

class HomeNewsListAdapter :
    RecyclerView.Adapter<HomeNewsListAdapter.ViewHolder>() {

    lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    private var dataList: MutableList<News> = mutableListOf()

    fun setUpdatedData(dataList: List<News>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemHomeNewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: News) {
            with(binding) {
                textviewItemDescription.text = data.description
                textviewItemTitle.text = data.title
                textviewItemTitle.typeface = Typeface.DEFAULT_BOLD
                imageviewItemImage.load(data.imageUrl) {
                    crossfade(true)
                }
                textviewItemAuthor.text = data.author?.fullName ?: ""
                textviewItemDate.text = data.dateTime
            }
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
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
        viewHolder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

}