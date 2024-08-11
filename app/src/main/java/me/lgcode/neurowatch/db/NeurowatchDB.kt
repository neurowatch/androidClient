package me.lgcode.neurowatch.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [VideoClipEntity::class, DetectedObjectEntity::class, TokenEntity::class],
    version = 1
)
abstract class NeurowatchDB: RoomDatabase() {
    abstract fun videoClipDao(): VideoClipDao
}