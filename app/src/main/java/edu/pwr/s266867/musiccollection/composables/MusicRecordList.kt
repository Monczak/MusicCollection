package edu.pwr.s266867.musiccollection.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.pwr.s266867.musiccollection.R
import edu.pwr.s266867.musiccollection.musicdata.MusicRecord
import edu.pwr.s266867.musiccollection.musicdata.MusicTrack
import edu.pwr.s266867.musiccollection.ui.theme.MusicCollectionTheme

@Composable
fun MusicRecordList(musicRecords: List<MusicRecord>, onMusicRecordSelected: (MusicRecord) -> Unit) {
    LazyColumn {
        items(musicRecords) { record ->
            MusicRecordListItem(musicRecord = record, onMusicRecordSelected = onMusicRecordSelected)
        }
    }
}

@Composable
fun MusicRecordListItem(musicRecord: MusicRecord, onMusicRecordSelected: (MusicRecord) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onMusicRecordSelected(musicRecord) }
    ) {
        Image(
            painter = painterResource(id = musicRecord.coverResId),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = musicRecord.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = musicRecord.artist, style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MusicRecordListPreview() {
    MusicCollectionTheme {
        MusicRecordList(musicRecords = listOf(
            MusicRecord(0, "Title 1", "Artist 1", "Genre 1", 2000, R.drawable.ic_launcher_background, emptyList()),
            MusicRecord(1, "Title 2", "Artist 2", "Genre 2", 2001, R.drawable.ic_launcher_background, emptyList()),
            MusicRecord(2, "Title 3", "Artist 3", "Genre 3", 2002, R.drawable.ic_launcher_background, emptyList()),
        ), onMusicRecordSelected = { record -> return@MusicRecordList })
    }
}