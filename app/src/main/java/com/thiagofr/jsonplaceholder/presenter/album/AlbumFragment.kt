package com.thiagofr.jsonplaceholder.presenter.album

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
import com.thiagofr.jsonplaceholder.R
import com.thiagofr.jsonplaceholder.databinding.FragmentAlbumBinding
import com.thiagofr.jsonplaceholder.model.AlbumUI
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumFragment : Fragment() {

    private val binding by lazy {
        FragmentAlbumBinding.inflate(layoutInflater)
    }

    private val viewModel: AlbumViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val album = getAlbum()
        viewModel.viewState.observe(viewLifecycleOwner) {
            it?.let {
                handleViewState(it)
            }
        }
        viewModel.dispatchAction(AlbumViewAction.InitView(album))
    }

    private fun handleViewState(viewState: AlbumViewState) {
        when (viewState) {
            is AlbumViewState.SetupViewState -> setupView(viewState.albumUI)
            AlbumViewState.ErrorViewState -> setErrorViewState()
        }
    }

    private fun setupView(albumUI: AlbumUI) {
        setupToolbar(albumUI.title)
    }

    private fun setErrorViewState() {
        binding.contentGroup.isGone = true
        binding.errorGroup.isVisible = true
    }

    private fun setupToolbar(title: String) {
        with(binding) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            toolbar.title = title
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun getAlbum(): AlbumUI? {
        val album: AlbumUI? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ALBUM_ARG, AlbumUI::class.java)
        } else {
            arguments?.getParcelable(ALBUM_ARG)
        }
        return album
    }

    companion object {
        private const val ALBUM_ARG = "album"
    }
}