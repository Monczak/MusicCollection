package edu.pwr.s266867.musiccollection.musicdata

import android.net.Uri

data class MusicRecord(
    val id: Int,
    val title: String,
    val artist: String,
    val genre: String,
    val year: Int,
    val coverResId: Int,
    val description: String,
    val tracks: List<MusicTrack>,
    val galleryPhotoResIds: List<Int>,
    val videoUris: List<Uri>,
)
