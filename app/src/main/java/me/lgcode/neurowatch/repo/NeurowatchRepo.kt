package me.lgcode.neurowatch.repo

import me.lgcode.neurowatch.api.NeurowatchApi
import me.lgcode.neurowatch.model.VideoClip
import timber.log.Timber
import javax.inject.Inject

class NeurowatchRepo @Inject constructor(val api: NeurowatchApi) {
    
    suspend fun getVideos(): Result<List<VideoClip>> {
        val result = api.getVideos()
        return if (result.isSuccessful) {
            result.body()?.let { 
                Result.success(it)
            } ?: Result.failure(Throwable("videos could not be fetch"))
        } else {
            Timber.e(result.errorBody()?.string())
            Result.failure(Throwable("videos could not be fetch"))
        }
    }
}