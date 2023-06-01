package com.sportzinteractive.baseprojectsetup.ui.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sportzinteractive.baseprojectsetup.utils.showToast

fun AppCompatActivity.handleMessageEvents(viewModel: BaseViewModel) {
    viewModel.message.observe(this) {
        it.getContentIfNotHandled()?.let { message ->
            showToast(message)
        }
    }
}

fun Fragment.handleMessageEvents(viewModel: BaseViewModel) {
    viewModel.message.observe(viewLifecycleOwner) {
        it.getContentIfNotHandled()?.let { message ->
            requireActivity().showToast(message)
        }
    }
}
