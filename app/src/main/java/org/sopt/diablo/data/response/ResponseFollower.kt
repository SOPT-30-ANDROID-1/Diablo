package org.sopt.diablo.data.response

import com.google.gson.annotations.SerializedName

data class ResponseFollower(
    @SerializedName("login")
    val id: String,
    val avatar_url: String,
    val html_url: String
)