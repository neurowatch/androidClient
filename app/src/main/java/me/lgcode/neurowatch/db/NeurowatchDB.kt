package me.lgcode.neurowatch.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [VideoClipEntity::class, TokenEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    UriTypeConverter::class, 
    LocalDateTimeTypeConverter::class, 
    DetectedObjectListTypeConverter::class
)
abstract class NeurowatchDB: RoomDatabase() {
    abstract fun videoClipDao(): VideoClipDao
    abstract fun tokenDao(): TokenDao
}