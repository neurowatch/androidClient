package me.lgcode.neurowatch.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "detected_object",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = VideoClipEntity::class,             
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("videoClipId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class DetectedObjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val objectName: String,
    val timestamp: Int,
    val detectionConfidence: Float,
    @ColumnInfo(index = true) val videoClipId: Long
)
