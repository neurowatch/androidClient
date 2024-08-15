package me.lgcode.neurowatch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.lgcode.neurowatch.datasource.TokenProvider
import me.lgcode.neurowatch.db.TokenDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Singleton
    @Provides
    fun provideTokenProvider(tokenDao: TokenDao): TokenProvider {
        return TokenProvider(tokenDao)
    }
}