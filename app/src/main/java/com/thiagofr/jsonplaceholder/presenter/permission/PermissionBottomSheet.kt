package com.thiagofr.jsonplaceholder.presenter.permission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.thiagofr.jsonplaceholder.databinding.PermissionBottomSheetFragmentBinding

class PermissionBottomSheetFragment : BottomSheetDialogFragment() {

    private val binding: PermissionBottomSheetFragmentBinding by lazy {
        PermissionBottomSheetFragmentBinding.inflate(layoutInflater)
    }

    var showCallBack: (() -> Unit)? = null
    var closeCallBack: (() -> Unit)? = null
    var okCallBack: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOk.setOnClickListener {
            okCallBack?.invoke()
            dismiss()
        }
        binding.btnDeny.setOnClickListener {
            closeCallBack?.invoke()
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        showCallBack?.invoke()
    }
}