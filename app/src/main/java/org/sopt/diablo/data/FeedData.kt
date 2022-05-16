package org.sopt.diablo.data

data class FeedData(
    val title: String,
    val profile: String,
    val description: String,
    val images: List<FeedImageData>
)

data class FeedImageData(
    val url: String,
    val isVideo: Boolean,
    val isStared: Boolean
)