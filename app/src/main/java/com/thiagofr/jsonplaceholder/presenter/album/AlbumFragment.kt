package com.thiagofr.jsonplaceholder.presenter.album

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.thiagofr.jsonplaceholder.databinding.FragmentAlbumBinding
import com.thiagofr.jsonplaceholder.model.AlbumUI
import com.thiagofr.jsonplaceholder.model.PhotoUI
import com.thiagofr.jsonplaceholder.presenter.album.adapter.PhotoAdapter
import com.thiagofr.jsonplaceholder.presenter.info.InfoBottomSheetFragment
import com.thiagofr.jsonplaceholder.presenter.permission.PermissionBottomSheetFragment
import com.thiagofr.jsonplaceholder.util.checkExternalStoragePermission
import org.koin.androidx.viewmodel.ext.android.viewModel


class AlbumFragment : Fragment() {

    private val binding by lazy {
        FragmentAlbumBinding.inflate(layoutInflater)
    }

    private val viewModel: AlbumViewModel by viewModel()

    private val register = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { allResult ->

        val permissionsDeny = allResult.filter { !it.value }

        when (permissionsDeny.isEmpty()) {
            true -> {
                handleGrantedPermission()
            }

            false -> showInfoDisclaimer()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val album = getAlbum()
        viewModel.dispatchAction(AlbumViewAction.InitViewAction(album))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) {
            it?.let {
                handleViewState(it)
            }
        }
    }

    private fun handleViewState(viewState: AlbumViewState) {
        when (viewState) {
            is AlbumViewState.SetupViewState -> setupView(viewState.albumUI)
            AlbumViewState.ErrorViewState -> setErrorViewState()
            is AlbumViewState.SetAlbum -> handleSetAlbumViewState(viewState.album)
            is AlbumViewState.OpenImage -> handleOpenImage(viewState.data)
            is AlbumViewState.RequestPermissions -> handleRequestPermissions(viewState.permissions)
        }
    }

    private fun handleGrantedPermission() {
        viewModel.dispatchAction(AlbumViewAction.GetImageAfterPermission)
    }

    private fun handleRequestPermissions(permissions: Array<String>) {
        binding.scrimView.isGone = true
        register.launch(permissions)
    }

    private fun handleSetAlbumViewState(album: List<PhotoUI>) {
        with(binding) {
            rvAlbum.layoutManager = GridLayoutManager(requireContext(), COLUMN_COUNT)
            rvAlbum.adapter = PhotoAdapter(album) {
                downLoadingImage(it)
            }
            contentShimmer.stopShimmer()
            contentShimmer.isGone = true
            rvAlbum.isVisible = true
        }
    }

    private fun setupView(albumUI: AlbumUI) {
        setupToolbar(albumUI.title)
        with(binding) {
            contentShimmer.startShimmer()
            contentShimmer.isVisible = true
        }
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

    private fun downLoadingImage(photo: PhotoUI) {

        val permissions = getPermissions()

        if (checkExternalStoragePermission(permissions)) {
            viewModel.dispatchAction(AlbumViewAction.GetImageAction(photo))
        } else {
            showPermissionDisclaimer(photo, permissions)
        }
    }

    private fun getPermissions() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES
        )
    } else {
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }


    private fun showPermissionDisclaimer(photo: PhotoUI, permissions: Array<String>) {
        val bottomSheet = PermissionBottomSheetFragment().apply {
            showCallBack = {
                binding.scrimView.isVisible = true
            }
            closeCallBack = {
                binding.scrimView.isGone = true
            }
            okCallBack = {
                viewModel.dispatchAction(
                    AlbumViewAction.RequestPermissionAction(
                        photo,
                        permissions
                    )
                )
            }
            this.isCancelable = false
        }

        bottomSheet.show(childFragmentManager, bottomSheet.tag)
    }

    private fun showInfoDisclaimer() {
        val bottomSheet = InfoBottomSheetFragment().apply {
            showCallBack = {
                binding.scrimView.isVisible = true
            }
            okCallBack = {
                binding.scrimView.isGone = true
            }
            this.isCancelable = false
        }

        bottomSheet.show(childFragmentManager, bottomSheet.tag)
    }

    private fun handleOpenImage(imageUri: Uri) {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            setDataAndType(imageUri, "image/*")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context?.startActivity(intent)
    }

    companion object {
        private const val ALBUM_ARG = "album"
        private const val COLUMN_COUNT = 3
    }
}