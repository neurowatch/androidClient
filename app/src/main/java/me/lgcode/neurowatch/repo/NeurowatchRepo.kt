package me.lgcode.neurowatch.repo

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.lgcode.neurowatch.api.NeurowatchApi
import me.lgcode.neurowatch.datasource.NeurowatchDataSource
import me.lgcode.neurowatch.model.VideoClip
import timber.log.Timber
import javax.inject.Inject

class NeurowatchRepo @Inject constructor(val dataSource: NeurowatchDataSource) {
    
    fun getVideos(): Flow<PagingData<VideoClip>> {
        return dataSource.getVideos().map { pagingData ->
            pagingData.map { it }
        }
    }
}