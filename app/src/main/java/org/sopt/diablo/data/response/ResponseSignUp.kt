package org.sopt.diablo.data.response

import com.google.gson.annotations.SerializedName

data class ResponseSignUp(
    @SerializedName("email")
    val id: String,
    val name: String
)