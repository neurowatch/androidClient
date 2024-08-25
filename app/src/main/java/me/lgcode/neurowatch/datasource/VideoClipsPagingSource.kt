package me.lgcode.neurowatch.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.lgcode.neurowatch.api.NeurowatchApi
import me.lgcode.neurowatch.model.VideoClip
import timber.log.Timber
import javax.inject.Inject

class VideoClipsPagingSource @Inject constructor(val api: NeurowatchApi) : PagingSource<Int, VideoClip>() {
    
    override fun getRefreshKey(state: PagingState<Int, VideoClip>) =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoClip> {
        val page = params.key ?: 0
        val result = api.getVideos(page)

        return if (result.isSuccessful) {
            result.body()?.let {
                val nextKey = if (!it.next.isNullOrEmpty()) {
                    null
                } else {
                    page + 1
                }
                LoadResult.Page(
                    data = it.results,
                    prevKey = null,
                    nextKey = nextKey
                )
            } ?: LoadResult.Error(Throwable("videos could not be fetch"))
        } else {
            Timber.e(result.errorBody()?.string())
            LoadResult.Error(Throwable("videos could not be fetch"))
        }
    }
}