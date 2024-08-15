package me.lgcode.neurowatch.repo

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.lgcode.neurowatch.api.NeurowatchApi
import me.lgcode.neurowatch.datasource.NeurowatchDataSource
import me.lgcode.neurowatch.model.LoginRequest
import me.lgcode.neurowatch.model.VideoClip
import me.lgcode.neurowatch.model.toModel
import timber.log.Timber
import javax.inject.Inject

class NeurowatchRepo @Inject constructor(val dataSource: NeurowatchDataSource) {
    
    fun getVideos(): Flow<PagingData<VideoClip>> {
        return dataSource.getVideos().map { pagingData ->
            pagingData.map { it.toModel() }
        }
    }
    
    suspend fun getVideo(id: Int): Result<VideoClip?> = dataSource.getVideo(id)

    suspend fun login(loginRequest: LoginRequest) = dataSource.login(loginRequest)
    
    suspend fun getToken() = dataSource.getToken()
    
    suspend fun clearToken() = dataSource.clearToken()
}