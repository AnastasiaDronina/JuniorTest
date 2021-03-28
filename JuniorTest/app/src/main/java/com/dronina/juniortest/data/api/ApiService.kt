package com.dronina.juniortest.data.api

import com.dronina.juniortest.data.model.Medication
import retrofit2.http.GET

interface ApiService {
    @GET("meds")
    suspend fun getMeds(): List<Medication>
}