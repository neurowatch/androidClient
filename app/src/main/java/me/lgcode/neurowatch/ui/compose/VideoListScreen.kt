package me.lgcode.neurowatch.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import me.lgcode.neurowatch.model.VideoClip
import me.lgcode.neurowatch.viewModel.VideoListViewModel
import java.time.format.DateTimeFormatter 
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

@Composable
fun VideoListScreen(
    viewModel: VideoListViewModel = viewModel(),
    navController: NavController
) {
    
    val videoClipsList = viewModel.videoClips.collectAsLazyPagingItems()
    
    Scaffold(
        
    ) {
        Box(modifier = Modifier.padding(it)) {
            VideoClipList(
                videoClipsList = videoClipsList,
                onVideoClipClick = {
                }
            )
        }
    }
    
}

@Composable
fun VideoClipList(videoClipsList: LazyPagingItems<VideoClip>, onVideoClipClick: (VideoClip) -> Unit) {
    LazyColumn {
        items(videoClipsList.itemCount) {
            videoClipsList[it]?.let { videoClip ->
                VideoClipItem(videoClip = videoClip, onClick = onVideoClipClick)
            }
        }
    }
}

@Composable
fun VideoClipItem(videoClip: VideoClip, onClick: (VideoClip) -> Unit) {
    Column(
        modifier = Modifier
            .clickable {
                onClick(videoClip)
            }
    ) {
        AsyncImage(model = videoClip.thumbnail, contentDescription = null)
        Text(text = formatter.format(videoClip.date))
    }
}