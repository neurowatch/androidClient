package me.lgcode.neurowatch.model

sealed interface VideoDetailState {
    data class Success(val video: VideoClip) : VideoDetailState
    data class Error(val throwable: Throwable) : VideoDetailState
}