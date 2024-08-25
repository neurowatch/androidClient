package me.lgcode.neurowatch.ui.compose

import android.content.Context
import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.lgcode.neurowatch.viewModel.LoginViewModel
import me.lgcode.neurowatch.viewModel.VideoDetailViewModel
import me.lgcode.neurowatch.viewModel.VideoListViewModel
import timber.log.Timber

@Composable
fun NeurowatchNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = "login",
) {
    
    val navController = rememberNavController()
    val context = LocalContext.current
    
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable("login") {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(viewModel, navController)
            SetOrientation(context, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        }
        composable("settings") {
            // TODO: Customize settings from the app
        }
        composable("video_detail/{id}") { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id")?.toInt()
            val viewModel = hiltViewModel<VideoDetailViewModel>()
            
            VideoDetailScreen(
                viewModel = viewModel,
                videoId = id!!,
            )
            SetOrientation(context, ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
        }
        composable("video_list") {
            val viewModel = hiltViewModel<VideoListViewModel>()
            VideoListScreen(viewModel, navController)
            SetOrientation(context, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        }
        composable("finalize") {
            context as ComponentActivity
            context.finish()
        }
    }
}

@Composable
fun SetOrientation(context: Context, orientation: Int) {
    val activity = context as ComponentActivity
    LaunchedEffect(Unit) {
        activity.requestedOrientation = orientation
    }
}