package me.lgcode.neurowatch.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.lgcode.neurowatch.model.VideoDetailState
import me.lgcode.neurowatch.repo.NeurowatchRepo
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VideoDetailViewModel @Inject constructor(val repo: NeurowatchRepo): ViewModel() {
    
    private val _videoDetailState = MutableStateFlow<VideoDetailState?>(null)
    val videoDetailState = _videoDetailState.asStateFlow()
    
    fun fetchVideo(id: Int) {
        viewModelScope.launch { 
            repo.getVideo(id).fold(
                onSuccess = { videoClip ->
                    videoClip?.let {
                        _videoDetailState.value = VideoDetailState.Success(videoClip)
                    }
                },
                onFailure = {
                    Timber.e(it)
                    _videoDetailState.value = VideoDetailState.Error(it)
                }
            )
        }
    }
    
}