package com.thiagofr.jsonplaceholder.presenter.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.jsonplaceholder.databinding.FragmentUserListBinding
import com.thiagofr.jsonplaceholder.model.UserUI
import com.thiagofr.jsonplaceholder.presenter.userlist.adapter.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment() {

    private val binding by lazy {
        FragmentUserListBinding.inflate(layoutInflater)
    }

    private val viewModel: UserListViewModel by viewModel()

    private val adapter: UserListAdapter = UserListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            state?.let {
                handleViewState(state)
            }
        }

        with(binding.rvUserList) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = this@UserListFragment.adapter
        }
        viewModel.dispatchAction(UserListViewAction.Init)
    }

    private fun handleViewState(state: UserListViewState) {
        when (state) {
            is UserListViewState.SetUserList -> handleSetUserList(state.userList)
        }
    }

    private fun handleSetUserList(userList: List<UserUI>) {
        adapter.addList(userList)
    }
}