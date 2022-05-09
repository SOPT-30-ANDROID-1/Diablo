package org.sopt.diablo.data.response

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T? = null
)
