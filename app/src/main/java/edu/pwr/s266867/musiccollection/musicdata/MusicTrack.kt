package edu.pwr.s266867.musiccollection.musicdata

data class MusicTrack(
    val name: String,
    val lengthSeconds: Int,
    val artists: List<String>,
)
