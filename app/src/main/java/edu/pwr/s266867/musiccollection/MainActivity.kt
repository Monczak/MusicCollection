package edu.pwr.s266867.musiccollection

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.pwr.s266867.musiccollection.composables.MusicRecordList
import edu.pwr.s266867.musiccollection.musicdata.MusicRecord
import edu.pwr.s266867.musiccollection.ui.theme.MusicCollectionTheme
import edu.pwr.s266867.musiccollection.util.RecordReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val records = RecordReader.readRecordsFromYaml(this)

        setContent {
            MusicCollectionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MusicRecordList(musicRecords = records) {
                        it.tracks.forEach {track -> Log.d("DEBUG", track.toString())}
                    }
                }
            }
        }
    }
}