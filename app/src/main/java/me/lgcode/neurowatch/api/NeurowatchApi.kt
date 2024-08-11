package me.lgcode.neurowatch.api

import me.lgcode.neurowatch.model.LoginRequest
import me.lgcode.neurowatch.model.LoginResponse
import me.lgcode.neurowatch.model.Settings
import me.lgcode.neurowatch.model.VideoClip
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NeurowatchApi {
    
    @GET("videos")
    suspend fun getVideos(
        @Query("page") page: Int
    ): Response<List<VideoClip>>
    
    @GET("settings")
    suspend fun getSettings(): Response<Settings>
    
    @POST
    suspend fun updateSettings(@Body settings: Settings): Response<Unit>
    
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
    
    @POST("logout")
    suspend fun logout(): Response<Unit>
}