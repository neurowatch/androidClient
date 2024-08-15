package me.lgcode.neurowatch.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.lgcode.neurowatch.db.VideoClipEntity
import java.time.LocalDateTime

@Parcelize
data class VideoClip(
    val id: Int,
    val videoUrl: Uri,
    val thumbnail: Uri,
    val date: LocalDateTime,
    val detectedObjects: List<DetectedObject>,
): Parcelable

fun VideoClip.toEntity(): VideoClipEntity {
    return VideoClipEntity(
        id = id,
        videoUrl = videoUrl.toString(),
        thumbnail = thumbnail.toString(),
        date = date.toString(),
        detectedObjects = detectedObjects,
    )
}

fun VideoClipEntity.toModel(): VideoClip {
    return VideoClip(
        id = id,
        videoUrl = Uri.parse(videoUrl),
        thumbnail = Uri.parse(thumbnail),
        date = LocalDateTime.parse(date),
        detectedObjects = detectedObjects,
    )
}