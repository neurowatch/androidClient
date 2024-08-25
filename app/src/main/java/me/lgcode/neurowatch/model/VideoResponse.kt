package me.lgcode.neurowatch.model

data class VideoResponse(
    val count: Int,
    val next: String?,
    val results: List<VideoClip>
)
