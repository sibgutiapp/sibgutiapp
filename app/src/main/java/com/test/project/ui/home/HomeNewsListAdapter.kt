package com.test.project.ui.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.project.R
import com.test.project.databinding.ItemHomeNewsListBinding
import com.test.project.domain.entity.News

class HomeNewsListAdapter() :
    RecyclerView.Adapter<HomeNewsListAdapter.ViewHolder>() {

    lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    private var dataList: MutableList<News> = mutableListOf()
    private var favoriteNews= mutableSetOf(2)

    fun setUpdatedData(dataList: List<News>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemHomeNewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
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

                if (favoriteNews.contains(data.id)) {
                    addToFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_selected)
                    addToFavorite.background = addToFavorite.context.getDrawable(R.drawable.favorite_button_border_selected)
                    addToFavoriteTextview.setTextColor(addToFavorite.context.getColor(R.color.navy_blue))
                    addToFavoriteTextview.text = "Добавлено"
                }
            }
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
            binding.addToFavorite.setOnClickListener { onFavoriteButtonClick(binding, data.id) }
            binding.addToFavoriteButton.setOnClickListener { onFavoriteButtonClick(binding, data.id) }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun onFavoriteButtonClick(binding: ItemHomeNewsListBinding, id: Int) {
        with(binding) {
            if (!favoriteNews.contains(id)) {
                addToFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_selected)
                addToFavorite.background = addToFavorite.context.getDrawable(R.drawable.favorite_button_border_selected)
                addToFavoriteTextview.setTextColor(addToFavorite.context.getColor(R.color.navy_blue))
                addToFavoriteTextview.text = "Добавлено"
                // todo add from db
                favoriteNews.add(id)
            } else {
                addToFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_normal)
                addToFavorite.background = addToFavorite.context.getDrawable(R.drawable.favorite_button_border_normal)
                addToFavoriteTextview.setTextColor(addToFavorite.context.getColor(R.color.default_text_color))
                addToFavoriteTextview.text = "В избранное"
                //todo remove to db
                favoriteNews.remove(id)
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