package me.lgcode.neurowatch.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.lgcode.neurowatch.model.Token

@Dao
interface TokenDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(token: TokenEntity)
    
    @Query("SELECT * FROM tokens LIMIT 1")
    suspend fun get(): TokenEntity

    @Query("DELETE FROM tokens")
    suspend fun clear()
    
}