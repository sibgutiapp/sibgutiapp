package com.test.project.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.project.databinding.ItemProfileFriendsListBinding
import com.test.project.domain.entity.Friend

class ProfileRecyclerViewFriendsListAdapter :
    RecyclerView.Adapter<ProfileRecyclerViewFriendsListAdapter.ViewHolder>() {

    private var dataList: List<Friend>? = null

    fun setUpdatedData(dataList: List<Friend>) {
        this.dataList = dataList
    }

    class ViewHolder(private val binding: ItemProfileFriendsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Friend) {
            with(binding) {
                textviewItemFriendsListGroup.text = data.group
                textviewItemFriendsListName.text = data.fullName
                textviewItemFriendsListPhone.text = data.phone
                imageviewItemFriendsListAvatar.load(data.avatarUrl)
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
        holder.bind(dataList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(dataList == null) {
            return 0
        }
        return dataList!!.size
    }
}