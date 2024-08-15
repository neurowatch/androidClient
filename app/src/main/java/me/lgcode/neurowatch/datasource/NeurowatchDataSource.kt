@file:OptIn(ExperimentalPagingApi::class)

package me.lgcode.neurowatch.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.lgcode.neurowatch.api.NeurowatchApi
import me.lgcode.neurowatch.db.NeurowatchDB
import me.lgcode.neurowatch.db.TokenDao
import me.lgcode.neurowatch.db.VideoClipDao
import me.lgcode.neurowatch.db.VideoClipEntity
import me.lgcode.neurowatch.model.LoginRequest
import me.lgcode.neurowatch.model.Token
import me.lgcode.neurowatch.model.VideoClip
import me.lgcode.neurowatch.model.toEntity
import me.lgcode.neurowatch.model.toModel
import timber.log.Timber
import javax.inject.Inject

class NeurowatchDataSource @Inject constructor(
    private val api: NeurowatchApi,
    private val videoClipsRemoteMediator: VideoClipsRemoteMediator,
    private val videoClipDao: VideoClipDao,
    private val tokenDao: TokenDao,
) {
    fun getVideos(): Flow<PagingData<VideoClipEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            remoteMediator = videoClipsRemoteMediator,
            pagingSourceFactory = {
                videoClipDao.pagingSource()
            }
        ).flow
    }
    
    suspend fun getVideo(id: Int): Result<VideoClip?> {
        return try {
            Result.success(videoClipDao.get(id)?.toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun login(loginRequest: LoginRequest): Result<Token> {
        return try {
            val response = api.login(loginRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    tokenDao.insert(it.toEntity())
                    Result.success(it)
                } ?: Result.failure(Exception("Token not retrieved"))
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getToken(): Result<Token> =
        try {
            Result.success(tokenDao.get().toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    
    suspend fun clearToken(): Result<Unit> = 
        try { 
            tokenDao.clear()
            Result.success(Unit) 
        } catch (e: Exception) {
            Result.failure(e)
        }
}