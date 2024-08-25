package me.lgcode.neurowatch.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import me.lgcode.neurowatch.model.DetectedObject

@Entity(tableName = "video_clips")
data class VideoClipEntity(
    @PrimaryKey val id: Int,
    val videoUrl: String,
    val thumbnail: String,
    val date: String,
    @TypeConverters(DetectedObjectListTypeConverter::class)
    val detectedObjects: List<DetectedObject>?,
)
