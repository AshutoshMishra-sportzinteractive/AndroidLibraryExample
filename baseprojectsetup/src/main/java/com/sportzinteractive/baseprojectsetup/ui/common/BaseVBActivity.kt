package com.sportzinteractive.baseprojectsetup.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseVBActivity<VB : ViewDataBinding>(private val layoutId: Int) : AppCompatActivity() {

    private var _binding: VB? = null
    val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, layoutId)
        binding?.lifecycleOwner = this
        binding?.executePendingBindings()

    }
}