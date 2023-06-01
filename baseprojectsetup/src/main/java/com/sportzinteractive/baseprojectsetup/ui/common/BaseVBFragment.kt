package com.sportzinteractive.baseprojectsetup.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseVBFragment<VB : ViewDataBinding>(private val inflate: Inflate<VB>) :
    Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding


    private val loadingDialog by lazy {
        LoadingDialog(requireContext())
    }

    fun showLoader() {
        loadingDialog.show(true)
    }

    fun hideLoader() {
        loadingDialog.show(false)
    }

    fun showLoader(show: Boolean) = loadingDialog.show(show)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        _binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun toast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

}