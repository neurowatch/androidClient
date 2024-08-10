package me.lgcode.neurowatch.api

import me.lgcode.neurowatch.model.Settings
import me.lgcode.neurowatch.model.VideoClip
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NeurowatchApi {
    
    @GET("videos")
    suspend fun getVideos(): Response<List<VideoClip>>
    
    @GET("settings")
    suspend fun getSettings(): Response<Settings>
    
    @POST
    suspend fun updateSettings(@Body settings: Settings): Response<Unit>
    
}