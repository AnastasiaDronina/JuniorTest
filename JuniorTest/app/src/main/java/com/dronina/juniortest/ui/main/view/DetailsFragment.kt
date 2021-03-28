package com.dronina.juniortest.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dronina.juniortest.R
import com.dronina.juniortest.ui.base.BaseViewModelFactory.Companion.getViewModel
import com.dronina.juniortest.ui.main.viewmodel.DetailsViewModel
import com.dronina.juniortest.utils.data.CURRENT_MEDICATION


class DetailsFragment : Fragment() {
    private val detailsViewModel: DetailsViewModel by lazy {
        getViewModel {
            DetailsViewModel(arguments?.getParcelable(CURRENT_MEDICATION))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsViewModel.currentMedication.observe(viewLifecycleOwner, Observer { medication ->
            (activity as AppCompatActivity).supportActionBar?.title = medication.title
        })
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onStop() {
        (activity as AppCompatActivity).supportActionBar?.hide()
        super.onStop()
    }
}