package me.lgcode.neurowatch.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.lgcode.neurowatch.viewModel.LoginViewModel
import me.lgcode.neurowatch.viewModel.VideoListViewModel

@Composable
fun NeurowatchNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = "login",
) {
    
    val navController = rememberNavController()
    
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable("login") {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(viewModel, navController)
        }
        composable("settings") {
            
        }
        composable("video_detail/{id}") { navBackStackEntry ->
            //VideoDetailScreen(navBackStackEntry.arguments?.getString("id"))
        }
        composable("video_list") {
            val viewModel = hiltViewModel<VideoListViewModel>()
            VideoListScreen(viewModel, navController)
        }
    }
}
