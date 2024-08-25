package me.lgcode.neurowatch.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import me.lgcode.neurowatch.model.VideoClip
import me.lgcode.neurowatch.repo.NeurowatchRepo
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(repo: NeurowatchRepo): ViewModel() {
    
    val videoClips: Flow<PagingData<VideoClip>> = repo.getVideos().cachedIn(viewModelScope)
    
}