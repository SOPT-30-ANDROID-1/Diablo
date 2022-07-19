package org.sopt.diablo.data.service

import org.sopt.diablo.data.request.RequestSignIn
import org.sopt.diablo.data.request.RequestSignUp
import org.sopt.diablo.data.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ): Call<BaseResponse<ResponseSignUp>>

    @POST("auth/signin")
    fun postSignIn(
        @Body body: RequestSignIn
    ): Call<BaseResponse<ResponseSignIn>>
}