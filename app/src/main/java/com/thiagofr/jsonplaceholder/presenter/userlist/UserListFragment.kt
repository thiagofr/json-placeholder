package com.thiagofr.jsonplaceholder.presenter.userlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.jsonplaceholder.databinding.FragmentUserListBinding
import com.thiagofr.jsonplaceholder.presenter.userlist.adapter.UserListAdapter

class UserListFragment : Fragment() {

    private val binding by lazy {
        FragmentUserListBinding.inflate(layoutInflater)
    }

//    private val adapter: UserListAdapter = UserListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvUserList) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = UserListAdapter()
        }
    }
}