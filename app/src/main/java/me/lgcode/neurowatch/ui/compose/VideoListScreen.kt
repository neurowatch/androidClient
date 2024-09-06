package me.lgcode.neurowatch.ui.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import me.lgcode.neurowatch.model.VideoClip
import me.lgcode.neurowatch.viewModel.VideoListViewModel
import java.time.format.DateTimeFormatter 
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import me.lgcode.neurowatch.R


val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListScreen(
    viewModel: VideoListViewModel = viewModel(),
    navController: NavController
) {
    val videoClipsList = viewModel.videoClips.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(text = stringResource(id = R.string.app_name)) 
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            VideoClipList(
                videoClipsList = videoClipsList,
                onVideoClipClick = { videoClip ->
                    navController.navigate("video_detail/${videoClip.id}")
                }
            )
        }
        BackHandler {
            navController.navigate("finalize")
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
            .padding(16.dp)
            .clickable {
                onClick(videoClip)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            model = videoClip.thumbnail, 
            contentDescription = null
        )
        Text(text = formatter.format(videoClip.date))
    }
}