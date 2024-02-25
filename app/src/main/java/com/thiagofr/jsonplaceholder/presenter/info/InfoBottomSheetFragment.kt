package com.thiagofr.jsonplaceholder.presenter.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.thiagofr.jsonplaceholder.databinding.InfoBottomSheetFragmentBinding

class InfoBottomSheetFragment : BottomSheetDialogFragment() {

    private val binding: InfoBottomSheetFragmentBinding by lazy {
        InfoBottomSheetFragmentBinding.inflate(layoutInflater)
    }

    var showCallBack: (() -> Unit)? = null
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
    }

    override fun onStart() {
        super.onStart()
        showCallBack?.invoke()
    }
}