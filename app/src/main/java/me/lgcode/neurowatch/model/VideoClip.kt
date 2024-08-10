package me.lgcode.neurowatch.model

import android.net.Uri

data class VideoClip(
    val id: Int,
    val videoUrl: Uri,
    val thumbnail: Uri,
    val detectedObjects: List<DetectedObject>
)
