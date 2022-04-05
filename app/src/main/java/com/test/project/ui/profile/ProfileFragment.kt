package com.test.project.ui.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.test.project.R
import android.viewbinding.library.fragment.viewBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.test.project.databinding.ProfileFragmentBinding

import com.test.project.domain.entity.ProfileMy
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding: ProfileFragmentBinding by viewBinding()
    private val model: ProfileViewModel by viewModel()
    private val adapterProfileRecyclerViewFriendsList: ProfileRecyclerViewFriendsListAdapter =
        ProfileRecyclerViewFriendsListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                model.userStateFlow.collect {
//                    showInfo(it)
//                }
                model.friendsListFlow.collect {
                    adapterProfileRecyclerViewFriendsList.setUpdatedData(it)
                    adapterProfileRecyclerViewFriendsList.notifyDataSetChanged()
                }
            }
        }
        bindUi(view.context)
    }

    private fun showInfo(user: ProfileMy) {
        with(viewBinding) {
            with(user) {
                imageviewProfileAvatar.load(avatarUrl)
                textviewProfileFullName.text = fullName
                textviewProfilePhone.text = phoneNumber
                textviewProfileGroup.text = group
            }
        }
    }

    private fun bindUi(context: Context) {
        with(viewBinding) {
            with(recyclerviewProfileFriendsList) {
                adapter = adapterProfileRecyclerViewFriendsList
                val managerRecyclerViewFriendsListAdapter = LinearLayoutManager(context)
                layoutManager = managerRecyclerViewFriendsListAdapter
                setHasFixedSize(true)
            }

            buttonProfileLogin.setOnClickListener {
                model.getUser()
                model.getFriends()
            }
        }
    }

}