package edu.pwr.s266867.musiccollection.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.pwr.s266867.musiccollection.R
import edu.pwr.s266867.musiccollection.musicdata.MusicRecord
import edu.pwr.s266867.musiccollection.ui.theme.MusicCollectionTheme
import kotlin.math.ceil

@Composable
fun MusicRecordGallery(galleryPhotoResIds: List<Int>, modifier: Modifier = Modifier, columnCount: Int = 3) {
    // Can't use LazyVerticalGrid due to Compose not allowing nested lazy columns/rows/grids/whatever
    // so here's a lousy grid implementation instead

    val rowCount: Int = ceil(galleryPhotoResIds.size.toDouble() / columnCount).toInt()
    var elems = 0

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        for (i in 1..rowCount) {
            Row {
                for (j in 1..columnCount) {
                    if (elems < galleryPhotoResIds.size) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .weight(1f),
                            painter = painterResource(id = galleryPhotoResIds[elems]),
                            contentDescription = null
                        )
                    }
                    else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .weight(1f),
                        )
                    }
                    elems++
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MusicRecordGalleryPreview() {
    MusicCollectionTheme {
        MusicRecordGallery(galleryPhotoResIds = List(5) { _ -> R.drawable.ic_launcher_background })
    }
}