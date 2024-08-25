package me.lgcode.neurowatch.di

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.lgcode.neurowatch.api.AuthenticationInterceptor
import me.lgcode.neurowatch.api.DateTypeAdapter
import me.lgcode.neurowatch.api.NeurowatchApi
import me.lgcode.neurowatch.api.UriTypeAdapter
import me.lgcode.neurowatch.datasource.TokenProvider
import me.lgcode.neurowatch.repo.NeurowatchRepo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    // TODO: Make this configurable
    private const val BASE_URL = "http://192.168.1.20:8000/api/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesAuthenticationInterceptor(tokenProvider: TokenProvider) = 
        AuthenticationInterceptor(tokenProvider)
    
    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authenticationInterceptor: AuthenticationInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authenticationInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(Uri::class.java, UriTypeAdapter())
        .registerTypeAdapter(LocalDateTime::class.java, DateTypeAdapter())
        .create()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    
    @Singleton
    @Provides
    fun provideNeurowatchApi(retrofit: Retrofit): NeurowatchApi = retrofit.create(NeurowatchApi::class.java)
}