package me.lgcode.neurowatch.api

import me.lgcode.neurowatch.model.FCMTokenRequest
import me.lgcode.neurowatch.model.LoginRequest
import me.lgcode.neurowatch.model.Settings
import me.lgcode.neurowatch.model.Token
import me.lgcode.neurowatch.model.VideoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NeurowatchApi {
    
    @GET("videos/")
    suspend fun getVideos(
        @Query("page") page: Int
    ): Response<VideoResponse>
    
    @GET("settings/")
    suspend fun getSettings(): Response<Settings>
    
    @POST
    suspend fun updateSettings(@Body settings: Settings): Response<Unit>
    
    @POST("login/")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<Token>
    
    @POST("fcm_token/")
    suspend fun saveFcmToken(
        @Body fcmToken: FCMTokenRequest
    ): Response<Unit>
}