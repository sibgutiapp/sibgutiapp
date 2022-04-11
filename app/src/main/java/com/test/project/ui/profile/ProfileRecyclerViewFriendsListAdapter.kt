package com.test.project.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.project.databinding.ItemProfileFriendsListBinding
import com.test.project.domain.entity.Friend

class ProfileRecyclerViewFriendsListAdapter :
    RecyclerView.Adapter<ProfileRecyclerViewFriendsListAdapter.ViewHolder>() {

    private var dataList: MutableList<Friend> = mutableListOf()

    fun setUpdatedData(dataList: List<Friend>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemProfileFriendsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Friend) {
            with(binding) {
                textviewItemGroup.text = data.group
                textviewItemName.text = data.fullName
                textviewItemPhone.text = data.phone
                imageviewItemAvatar.load(data.avatarUrl)
            }
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemProfileFriendsListBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

}