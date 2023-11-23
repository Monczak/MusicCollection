package edu.pwr.s266867.musiccollection.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.pwr.s266867.musiccollection.musicdata.MusicTrack
import edu.pwr.s266867.musiccollection.ui.theme.MusicCollectionTheme
import edu.pwr.s266867.musiccollection.util.Util

@Composable
fun MusicTrackList(tracks: List<MusicTrack>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        tracks.forEachIndexed { index, track ->
            MusicTrackListItem(index = index + 1, track = track)
        }
    }
}

@Composable
fun MusicTrackListItem(index: Int, track: MusicTrack, modifier: Modifier = Modifier) {
    Row(modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .width(32.dp)
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right,
                text = index.toString(),
                style = MaterialTheme.typography.bodyMedium)
        }
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(text = track.name, style = MaterialTheme.typography.bodyMedium)
            Text(text = track.artists.joinToString(", "), style = MaterialTheme.typography.bodySmall)
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 20.dp)
        ) {
            Text(text = Util.getTimestamp(track.lengthSeconds), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MusicTrackListItemPreview() {
    MusicCollectionTheme {
        MusicTrackListItem(index = 1, track = MusicTrack("Track 1", 60, listOf("Artist 1", "Artist 2")))
    }
}