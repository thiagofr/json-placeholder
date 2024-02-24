package com.thiagofr.jsonplaceholder.presenter.component

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.android.material.appbar.MaterialToolbar
import com.thiagofr.jsonplaceholder.R
import com.thiagofr.jsonplaceholder.databinding.SearchToolbarBinding

class SearchToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialToolbar(context, attrs, defStyleAttr) {

    private val binding: SearchToolbarBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.search_toolbar,
        this,
        true
    )

    private var searchViewEnterCallback: (() -> Unit)? = null

    var searchTermText = binding.searchView.text
        private set

    init {
        with(binding) {
            searchButton.setOnClickListener {
                onSearchIconClick()
            }
        }
        setSearchViewEnterAction()
    }

    override fun setTitle(@StringRes title: Int) {
        binding.title.setText(title)
    }

    override fun setTitle(title: CharSequence?) {
        binding.title.text = title
    }

    private fun onSearchIconClick() {
        with(binding) {
            searchButton.isGone = true
            title.isGone = true
            searchView.isVisible = true
        }
    }

    fun setSearchTextWatcher(textWatcher: TextWatcher) {
        binding.searchView.addTextChangedListener(textWatcher)
    }

    fun setSearchViewEnterCallback(callback: () -> Unit) {
        this.searchViewEnterCallback = callback
    }

    private fun setSearchViewEnterAction() {
        binding.searchView.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                binding.searchView.isGone = true
                binding.title.isVisible = true
                binding.searchButton.isVisible = true
                searchViewEnterCallback?.invoke()

                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.searchView.windowToken, 0)

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}