package edu.pwr.s266867.musiccollection.composables

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import edu.pwr.s266867.musiccollection.musicdata.MusicRecord
import edu.pwr.s266867.musiccollection.util.RecordReader

@Composable
fun HomeScreen(context: Context, modifier: Modifier = Modifier, navController: NavHostController) {
    val records = RecordReader.readRecords(context)

    Column(modifier = modifier) {
        MusicRecordList(musicRecords = records.values.toList()) {
            navController.navigate("details/${it.id}")
        }
    }
}

@Composable
fun DetailsScreen(musicRecord: MusicRecord, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        MusicRecordInfo(musicRecord = musicRecord)
    }
}