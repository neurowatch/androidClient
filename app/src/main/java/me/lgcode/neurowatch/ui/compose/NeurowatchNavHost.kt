package me.lgcode.neurowatch.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.lgcode.neurowatch.viewModel.VideoListViewModel

@Composable
fun NeurowatchNavHost(
    modifier: Modifier,
    startDestination: String = NavigationItem.VideoClipDetail.route
) {
    
    val navController = rememberNavController()
    
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Screen.LOGIN.name) {
            
        }
        composable(Screen.SETTINGS.name) {
            
        }
        composable(Screen.VIDEO_CLIP_DETAIL.name) { 
        }
        composable(Screen.VIDEO_CLIP_LIST.name) {
            val viewModel = hiltViewModel<VideoListViewModel>()
            VideoListScreen(viewModel, navController)
        }
    }
}

enum class Screen {
    LOGIN, 
    SETTINGS,
    VIDEO_CLIP_DETAIL,
    VIDEO_CLIP_LIST,
}

sealed class NavigationItem(val route: String) {
    data object Login : NavigationItem(Screen.LOGIN.name)
    data object Settings : NavigationItem(Screen.SETTINGS.name)
    data object VideoClipDetail : NavigationItem(Screen.VIDEO_CLIP_DETAIL.name)
    data object VideoClipList : NavigationItem(Screen.VIDEO_CLIP_LIST.name)
}