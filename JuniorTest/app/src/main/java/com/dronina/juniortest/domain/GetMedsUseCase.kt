package com.dronina.juniortest.domain

import com.dronina.juniortest.data.repository.MainRepository

class GetMedsUseCase(private val repo: MainRepository) {
    suspend fun invoke() = repo.getMeds()
}