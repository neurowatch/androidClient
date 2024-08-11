package me.lgcode.neurowatch.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.lgcode.neurowatch.model.VideoClip

@Dao
interface VideoClipDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(videoClips: List<VideoClip>)
    
    @Query("SELECT * FROM video_clips")
    fun pagingSource(): PagingSource<Int, VideoClip>
    
    @Query("DELETE FROM video_clips")
    suspend fun clearAll()
    
}