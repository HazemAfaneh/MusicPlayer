package com.hazem.spotifyclone.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazem.spotifyclone.exoplayer.MusicService
import com.hazem.spotifyclone.exoplayer.MusicServiceConnection
import com.hazem.spotifyclone.exoplayer.currentPlaybackPosition
import com.hazem.spotifyclone.other.Constants.UPDATE_PLAYER_POSITION_INTERVAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    musincServiceConnection: MusicServiceConnection
) :ViewModel() {

    private val playbackState = musincServiceConnection.playbackState

    private val _curSongDuration = MutableLiveData<Long>()
    val curSongDuraction:LiveData<Long> = _curSongDuration

    private val _curPlayerPosition = MutableLiveData<Long>()
    val curPlayerPosition:LiveData<Long> = _curPlayerPosition

    init {
        updateCurrentPlayerPosition()
    }
    private fun updateCurrentPlayerPosition(){
        viewModelScope.launch {
            while (true) //its not infinity loop because the scope will be cancelled
            {

                val pos = playbackState.value?.currentPlaybackPosition
                if(curPlayerPosition.value != pos)
                {
                    _curPlayerPosition.postValue(pos)
                    _curSongDuration.postValue(MusicService.curSongDuration)
                }
                delay(UPDATE_PLAYER_POSITION_INTERVAL)
            }
        }
    }
}