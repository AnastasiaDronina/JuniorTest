package com.dronina.juniortest.data.repository

import com.dronina.juniortest.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getMeds() = apiHelper.getMeds()
}