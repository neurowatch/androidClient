package me.lgcode.neurowatch.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class VideoClip(
    val id: Int,
    val videoUrl: Uri,
    val thumbnail: Uri,
    val date: LocalDateTime,
    val detectedObjects: List<DetectedObject>,
): Parcelable
