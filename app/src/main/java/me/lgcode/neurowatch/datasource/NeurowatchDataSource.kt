@file:OptIn(ExperimentalPagingApi::class)

package me.lgcode.neurowatch.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.lgcode.neurowatch.api.NeurowatchApi
import me.lgcode.neurowatch.db.NeurowatchDB
import me.lgcode.neurowatch.model.LoginRequest
import me.lgcode.neurowatch.model.LoginResponse
import me.lgcode.neurowatch.model.VideoClip
import timber.log.Timber
import javax.inject.Inject

class NeurowatchDataSource @Inject constructor(
    val api: NeurowatchApi,
    val db: NeurowatchDB
) {
    fun getVideos(): Flow<PagingData<VideoClip>> {
        val videoClipDao = db.videoClipDao()
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            remoteMediator = VideoClipsRemoteMediator(
                database = db,
                api = api,
            ),
            pagingSourceFactory = {
                videoClipDao.pagingSource()
            }
        ).flow
    }
    
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val response = api.login(loginRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Token not retrieved"))
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun logout(): Result<Unit> = try {
        val response = api.logout()
        if (response.isSuccessful) {
            Result.success(Unit)
        } else {
            Result.failure(Exception(response.errorBody()?.string()))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}