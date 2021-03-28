package com.dronina.juniortest.ui.main.viewmodel

import androidx.lifecycle.*
import com.dronina.juniortest.data.model.Medication
import com.dronina.juniortest.domain.GetMedsUseCase
import com.dronina.juniortest.utils.data.Resource
import kotlinx.coroutines.Dispatchers

class MedsViewModel(private val getMedsUseCase: GetMedsUseCase) : ViewModel() {
    private val retry = MutableLiveData<Boolean>()

    init {
        retry.value = true
    }

    val resource: LiveData<Resource<List<Medication>>> = Transformations.switchMap(retry) {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = getMedsUseCase.invoke()))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message.toString()))
            }
        }
    }

    fun retryLoading(retry: Boolean) {
        this.retry.value = retry
    }
}