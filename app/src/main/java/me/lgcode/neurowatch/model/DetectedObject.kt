package me.lgcode.neurowatch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetectedObject(
    val objectName: String,
    val timestamp: Int,
    val detectionConfidence: Float
): Parcelable
