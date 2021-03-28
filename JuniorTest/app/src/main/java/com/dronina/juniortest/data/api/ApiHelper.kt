package com.dronina.juniortest.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getMeds() = apiService.getMeds()
}