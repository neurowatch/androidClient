package me.lgcode.neurowatch.db

import android.net.Uri
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.lgcode.neurowatch.model.DetectedObject
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class UriTypeConverter {
    
    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()
    }
    
    @TypeConverter
    fun toUri(uriString: String?): Uri? {
        return uriString?.let { Uri.parse(it) }
    }
}

class LocalDateTimeTypeConverter {
    
    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): Long? {
        return dateTime?.toInstant(ZoneOffset.UTC)?.toEpochMilli()
    }
    
    @TypeConverter
    fun toLocalDateTime(timestamp: Long?): LocalDateTime? {
        return timestamp?.let {
            LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneOffset.UTC) 
        }
    }
}

class DetectedObjectListTypeConverter {
    val gson = Gson()
    val type = object : TypeToken<List<DetectedObject>>() {}.type
    
    @TypeConverter
    fun fromDetectedObject(detectedObjects: List<DetectedObject>?): String? {
        return gson.toJson(detectedObjects)
    }
    
    @TypeConverter
    fun toDetectedObject(detectedObjectsString: String?): List<DetectedObject>? {
        return gson.fromJson(detectedObjectsString, type)
    }
}