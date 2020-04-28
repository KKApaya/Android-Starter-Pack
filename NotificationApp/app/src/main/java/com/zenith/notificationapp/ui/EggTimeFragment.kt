package com.zenith.notificationapp.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zenith.notificationapp.R


class EggTimeFragment : Fragment(R.layout.egg_time_fragment) {

    companion object {
        fun newInstance() = EggTimeFragment()
    }

    private lateinit var viewModel: EggTimeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EggTimeViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
