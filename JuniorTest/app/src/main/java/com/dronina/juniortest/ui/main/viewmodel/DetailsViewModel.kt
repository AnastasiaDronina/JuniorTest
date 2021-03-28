package com.dronina.juniortest.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dronina.juniortest.data.model.Medication

class DetailsViewModel(private val medication: Medication?) : ViewModel() {
    val currentMedication = MutableLiveData<Medication>()

    init {
        medication?.let { currentMedication.value = it }
    }
}