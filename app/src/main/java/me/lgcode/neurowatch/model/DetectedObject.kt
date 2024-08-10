package me.lgcode.neurowatch.model

data class DetectedObject(
    val objectName: String,
    val timestamp: Int,
    val detectionConfidence: Float
)
