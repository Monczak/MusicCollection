package edu.pwr.s266867.musiccollection.composables

import android.annotation.SuppressLint
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import edu.pwr.s266867.musiccollection.R

@OptIn(UnstableApi::class) @SuppressLint("OpaqueUnitKey")
@Composable
fun VideoPlayer(modifier: Modifier = Modifier, videoUris: List<Uri>) {
    val context = LocalContext.current
    val mediaItems = videoUris.map { MediaItem.fromUri(it) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItems(mediaItems)
            prepare()
            videoScalingMode = C.VIDEO_SCALING_MODE_DEFAULT
        }
    }

    Box(modifier = modifier.fillMaxWidth().aspectRatio(16.0f / 9.0f)) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = true
                }
            }
        )
    }

    DisposableEffect(key1 = exoPlayer) {
        onDispose { exoPlayer.release() }
    }
}