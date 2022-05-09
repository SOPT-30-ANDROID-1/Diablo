package org.sopt.diablo.data.service

import org.sopt.diablo.data.response.ResponseFollower
import org.sopt.diablo.data.response.ResponseRepo
import org.sopt.diablo.data.response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{id}/followers")
    fun getFollowers(@Path("id")id: String): Call<List<ResponseFollower>>

    @GET("users/{id}/repos")
    fun getRepos(@Path("id")id: String): Call<List<ResponseRepo>>

    @GET("users/{id}")
    fun getProfile(@Path("id")id: String): Call<ResponseUser>
}