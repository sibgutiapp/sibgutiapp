package com.test.project.ui.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.project.databinding.ItemHomeListBinding


class HomeListAdapter :
    RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {
    val dataSet: Array<String> = arrayOf("Физика", "Химия", "Программирование")

    class ViewHolder(val binding: ItemHomeListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeListBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.nameTextView.text = dataSet[position]
        viewHolder.binding.nameTextView.typeface = Typeface.DEFAULT_BOLD
        viewHolder.binding.descriptionTextView.text = dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}