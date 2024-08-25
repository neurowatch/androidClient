package me.lgcode.neurowatch.ui.compose

import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import me.lgcode.neurowatch.model.VideoClip
import me.lgcode.neurowatch.model.VideoDetailState
import me.lgcode.neurowatch.viewModel.VideoDetailViewModel

@Composable
fun VideoDetailScreen(
    viewModel: VideoDetailViewModel = viewModel(),
    videoId: Int
) {
    val state = viewModel.videoDetailState.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.fetchVideo(videoId)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        when (state.value) {
            is VideoDetailState.Success -> 
                VideoPlayer(
                    video = (state.value as VideoDetailState.Success).video
                )
            else -> {
                // TODO: Error screen
            }
        }
    }
}

@Composable
fun VideoPlayer(video: VideoClip) {
    
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    
    val mediaSource = remember(video.videoUrl) {
        MediaItem.fromUri(video.videoUrl)
    }
    
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }
    
    DisposableEffect(Unit) {
        onDispose { 
            exoPlayer.release()
        }
    }
    
    Column {
        AndroidView(
            factory = { context -> 
                PlayerView(context).apply { 
                    player = exoPlayer
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}