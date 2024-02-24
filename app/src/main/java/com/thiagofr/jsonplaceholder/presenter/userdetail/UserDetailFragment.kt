package com.thiagofr.jsonplaceholder.presenter.userdetail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.jsonplaceholder.R
import com.thiagofr.jsonplaceholder.databinding.FragmentUserDetailBinding
import com.thiagofr.jsonplaceholder.model.AlbumUI
import com.thiagofr.jsonplaceholder.model.UserUI
import com.thiagofr.jsonplaceholder.presenter.userdetail.adapter.UserAlbumsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailFragment : Fragment() {

    private val binding by lazy {
        FragmentUserDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: UserDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = getUser()
        viewModel.viewState.observe(viewLifecycleOwner) {
            it?.let {
                handleViewState(it)
            }
        }
        viewModel.dispatchAction(UserDetailViewAction.InitView(user))
    }

    private fun handleViewState(viewState: UserDetailViewState) {
        when (viewState) {
            is UserDetailViewState.SetupViewState -> setupView(viewState.userUI)
            is UserDetailViewState.SetAlbumsViewState -> setAlbumListView(viewState.albumList)
            UserDetailViewState.ErrorViewState -> setErrorViewState()
        }
    }

    private fun setupView(user: UserUI) {
        with(binding) {
            tvName.text = user.name
            tvUsername.text = user.username
            tvEmail.text = user.email
            userAlbumsShimmer.startShimmer()
            userAlbumsShimmer.isVisible = true
        }
    }

    private fun setAlbumListView(albumList: List<AlbumUI>) {

        with(binding.rvAlbums) {
            layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = UserAlbumsAdapter(albumList) {
                it
            }
            isVisible = true
        }

        binding.userAlbumsShimmer.apply {
            stopShimmer()
            isGone = true
        }
    }

    private fun setErrorViewState() {
        binding.contentGroup.isGone = true
        binding.errorGroup.isVisible = true
    }

    private fun getUser(): UserUI? {
        val user: UserUI? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(USER_ARG, UserUI::class.java)
        } else {
            arguments?.getParcelable(USER_ARG)
        }
        return user
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title = requireContext().getString(R.string.user_detail_title)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        private const val USER_ARG = "user"
    }
}