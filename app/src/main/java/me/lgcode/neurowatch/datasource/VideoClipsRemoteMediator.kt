package me.lgcode.neurowatch.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import me.lgcode.neurowatch.api.NeurowatchApi
import me.lgcode.neurowatch.db.NeurowatchDB
import me.lgcode.neurowatch.model.VideoClip
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class VideoClipsRemoteMediator(
    private val database: NeurowatchDB,
    private val api: NeurowatchApi
): RemoteMediator<Int, VideoClip>() {
    val videoClipDao = database.videoClipDao()
    
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, VideoClip>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem =
                        state.lastItemOrNull() ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    lastItem.id / state.config.pageSize + 1
                }
            }

            val result = api.getVideos(loadKey)
            
            if (result.isSuccessful) {
                result.body()?.let {
                    database.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            videoClipDao.clearAll()
                        }
                        videoClipDao.insertAll(it)
                    }                    
                    MediatorResult.Success(
                        endOfPaginationReached = it.size < state.config.pageSize
                    )
                } ?: MediatorResult.Error(Throwable("videos could not be fetch")) 
            } else {
                Timber.e(result.errorBody()?.string())
                MediatorResult.Error(Throwable("videos could not be fetch"))
            }
        } catch (e: IOException) {
            Timber.e(e)
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            Timber.e(e)
            MediatorResult.Error(e)
        }
    }
}