package me.lgcode.neurowatch.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.lgcode.neurowatch.api.NeurowatchApi
import me.lgcode.neurowatch.model.VideoClip
import timber.log.Timber
import javax.inject.Inject

class NeurowatchDataSource @Inject constructor(val api: NeurowatchApi) {
    fun getVideos(): Flow<PagingData<VideoClip>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                VideoClipsPagingSource(api = api)
            }
        ).flow
    }
}