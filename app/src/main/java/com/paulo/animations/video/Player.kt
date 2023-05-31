package com.paulo.animations.video

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DataSpec

import com.google.android.exoplayer2.upstream.RawResourceDataSource

@Composable
fun VideoPlayer(exoPlayer: SimpleExoPlayer, code: Int) {
    val context = LocalContext.current


    AndroidView(
        modifier = Modifier.size(280.dp),
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer

            }
        }
    )


    // Load the video from the "raw" folder
    val rawVideoResId = code // Replace with your actual video name
    val dataSourceFactory = buildDataSourceFactory(context)
    val rawResourceDataSource = RawResourceDataSource(context)
    rawResourceDataSource.open(DataSpec(RawResourceDataSource.buildRawResourceUri(rawVideoResId)))
    val mediaSource = rawResourceDataSource.uri?.let {
        ProgressiveMediaSource.Factory(dataSourceFactory)
        .createMediaSource(it)
    }
    if (mediaSource != null) {
        exoPlayer.setMediaSource(mediaSource)
    }
    exoPlayer.prepare()
    exoPlayer.playWhenReady = true
    exoPlayer.isDeviceMuted = true


}

@Composable
fun rememberExoPlayer(context: Context): SimpleExoPlayer {
    return remember {
        val renderersFactory = DefaultRenderersFactory(context)
        SimpleExoPlayer.Builder(context, renderersFactory).build()
    }
}

private fun buildDataSourceFactory(context: Context): DataSource.Factory {
    val userAgent = "YourApplicationName"
    return DefaultDataSourceFactory(context, userAgent)
}
