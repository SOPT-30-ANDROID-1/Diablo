package org.sopt.diablo.data

import org.sopt.diablo.data.service.GithubService
import org.sopt.diablo.data.service.SoptService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL_GITHUB = "https://api.github.com/"
    private const val BASE_URL_SOPT = "http://13.124.62.236/"

    private val githubRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GITHUB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val soptRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_SOPT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val githubService: GithubService = githubRetrofit.create(GithubService::class.java)
    val soptService: SoptService = soptRetrofit.create(SoptService::class.java)
}