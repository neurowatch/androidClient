package me.lgcode.neurowatch.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.lgcode.neurowatch.db.NeurowatchDB
import me.lgcode.neurowatch.db.TokenDao
import me.lgcode.neurowatch.db.VideoClipDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NeurowatchDB {
        return Room.databaseBuilder(
            context = context,
            klass = NeurowatchDB::class.java,
            name = "neurowatch.db"
        ).build()
    }
    
    @Provides
    fun provideVideoDao(database: NeurowatchDB): VideoClipDao {
        return database.videoClipDao()
    }
    
    @Provides
    fun provideTokenDao(database: NeurowatchDB): TokenDao {
        return database.tokenDao()
    }

}