package org.sopt.diablo.data

import org.sopt.diablo.data.service.GithubService
import org.sopt.diablo.data.service.SoptService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL_GITHUB = "https://api.github.com/"
    private const val BASE_URL_SOPT = "http://13.124.62.236/"

    private fun buildRetrofit(url: String): Retrofit =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

    val githubService: GithubService = buildRetrofit(BASE_URL_GITHUB).create(GithubService::class.java)
    val soptService: SoptService = buildRetrofit(BASE_URL_SOPT).create(SoptService::class.java)
}