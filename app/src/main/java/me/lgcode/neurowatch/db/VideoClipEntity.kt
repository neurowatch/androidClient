package me.lgcode.neurowatch.db

import android.net.Uri
import androidx.room.Entity
import java.time.LocalDateTime

@Entity(tableName = "video_clips")
data class VideoClipEntity(
    val id: String,
    val videoUrl: String,
    val thumbnail: String,
    val date: String
)
