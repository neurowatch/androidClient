package me.lgcode.neurowatch.api

import kotlinx.coroutines.runBlocking
import me.lgcode.neurowatch.datasource.TokenProvider
import me.lgcode.neurowatch.repo.NeurowatchRepo
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(
    val tokenProvider: TokenProvider
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return if (
            request.url.encodedPath.contains("login") ||
            request.url.encodedPath.contains("fcm_token")
        ) {
            return chain.proceed(request)
        } else {
            val token = runBlocking { tokenProvider.getToken()?.token }
            val newRequest = request
                .newBuilder()
                .addHeader("Authorization", "token $token")
                .build()
            chain.proceed(newRequest)
        }
    }

}