package com.hazem.spotifyclone.exoplayer.callbacks

import android.widget.Toast
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.hazem.spotifyclone.exoplayer.MusicService

class MusicPlayerEventListener(
    private val musicService:MusicService
) :Player.Listener{
    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        super.onPlayerStateChanged(playWhenReady, playbackState)
        if(playbackState == Player.STATE_READY && !playWhenReady){
            musicService.stopForeground(false)//false means keep notification
        }
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        Toast.makeText(musicService, "An UKnown Error occurred", Toast.LENGTH_LONG).show()

    }

}