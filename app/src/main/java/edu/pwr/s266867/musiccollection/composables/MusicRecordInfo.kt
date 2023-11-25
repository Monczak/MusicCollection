package edu.pwr.s266867.musiccollection.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.pwr.s266867.musiccollection.R
import edu.pwr.s266867.musiccollection.musicdata.MusicRecord
import edu.pwr.s266867.musiccollection.musicdata.MusicTrack
import edu.pwr.s266867.musiccollection.ui.theme.MusicCollectionTheme


@Composable
fun MusicRecordInfo(musicRecord: MusicRecord, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(144.dp)
        ) {
            Column {
                Image(
                    painter = painterResource(id = musicRecord.coverResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(144.dp)
                        .clip(MaterialTheme.shapes.small)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Bottom)
            ) {
                Row {
                    Column {
                        Text(
                            modifier = Modifier.
                                padding(bottom = 8.dp),
                            text = musicRecord.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            modifier = Modifier.
                                padding(bottom = 16.dp),
                            text = musicRecord.artist,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            text = "${musicRecord.genre} • ${musicRecord.year}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(id = R.string.music_record_description_label),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = musicRecord.description,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Text(
            text = stringResource(id = R.string.music_track_list_label),
            style = MaterialTheme.typography.titleMedium
        )
        MusicTrackList(tracks = musicRecord.tracks)
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Text(
            text = stringResource(id = R.string.music_record_gallery_label),
            style = MaterialTheme.typography.titleMedium
        )
        MusicRecordGallery(galleryPhotoResIds = musicRecord.galleryPhotoResIds)
    }
}

@Preview(showBackground = true)
@Composable
fun MusicRecordInfoPreview() {
    MusicCollectionTheme {
        MusicRecordInfo(musicRecord = MusicRecord(
            0,
            "Title",
            "Artist",
            "Genre",
            2000,
            R.drawable.ic_launcher_background,
            "This is a lengthy description of this record. This record is cool. And so are its tracks. Wait, that's redundant.",
            listOf(
                MusicTrack("Track 1", 60, listOf("Artist")),
                MusicTrack("Track 2", 90, listOf("Artist")),
                MusicTrack("Track 3", 120, listOf("Artist", "Featured Artist")),
            ),
            List(5) { _ -> R.drawable.ic_launcher_background }
        ))
    }
}