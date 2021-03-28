package com.dronina.juniortest.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dronina.juniortest.R
import com.dronina.juniortest.data.api.ApiHelper
import com.dronina.juniortest.data.api.RetrofitBuilder
import com.dronina.juniortest.data.model.Medication
import com.dronina.juniortest.data.repository.MainRepository
import com.dronina.juniortest.domain.GetMedsUseCase
import com.dronina.juniortest.ui.base.BaseViewModelFactory.Companion.getViewModel
import com.dronina.juniortest.ui.main.adapter.MedsAdapter
import com.dronina.juniortest.ui.main.viewmodel.MedsViewModel
import com.dronina.juniortest.utils.data.CURRENT_MEDICATION
import com.dronina.juniortest.utils.data.Status.*
import com.dronina.juniortest.utils.ui.VerticalItemDecoration
import com.dronina.juniortest.utils.ui.navigate
import com.dronina.juniortest.utils.ui.setGone
import com.dronina.juniortest.utils.ui.showSnackbar
import kotlinx.android.synthetic.main.fragment_meds.*

class MedsFragment : Fragment(), MedsAdapter.OnItemClickListener {
    private val adapter: MedsAdapter by lazy { MedsAdapter(this) }
    private val medsViewModel: MedsViewModel by lazy {
        getViewModel { MedsViewModel(GetMedsUseCase(MainRepository((ApiHelper(RetrofitBuilder.apiService))))) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupObservers()
    }

    override fun onItemClick(currentMedication: Medication?) {
        currentMedication?.let { medication ->
            val data = Bundle()
            data.putParcelable(CURRENT_MEDICATION, medication)
            (activity as AppCompatActivity).navigate(DetailsFragment(), data)
        }
    }

    override fun onSootherIconClick() {
        view?.showSnackbar(
            text = R.string.kids_friendly_explained,
            action = R.string.ok
        )
    }

    private fun setupUi() {
        rvMeds.layoutManager = LinearLayoutManager(activity)
        rvMeds.addItemDecoration(VerticalItemDecoration(16))
        rvMeds.adapter = adapter

        btnTryAgain.setOnClickListener {
            medsViewModel.retryLoading(true)
        }
    }

    private fun setupObservers() {
        medsViewModel.resource.observe(viewLifecycleOwner, Observer { resource ->
            when (resource?.status) {
                ERROR -> showError()
                LOADING -> showLoading()
                SUCCESS -> {
                    resource.data?.let { data -> adapter.submitList(data as MutableList<Medication>) }
                    showSuccess()
                }
            }
        })
    }

    private fun showSuccess() {
        setGone(gone = false, rvMeds)
        setGone(gone = true, progressBar, tvError, btnTryAgain)
    }

    private fun showError() {
        setGone(gone = true, rvMeds, progressBar)
        setGone(gone = false, tvError, btnTryAgain)
    }

    private fun showLoading() {
        setGone(gone = true, rvMeds, tvError, btnTryAgain)
        setGone(gone = false, progressBar)
    }
}