@file:OptIn(ExperimentalMaterial3Api::class)

package edu.pwr.s266867.musiccollection.composables

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import edu.pwr.s266867.musiccollection.R
import edu.pwr.s266867.musiccollection.musicdata.MusicRecord
import edu.pwr.s266867.musiccollection.util.RecordReader

// TODO: Refactor this to use a common function for the TopAppBar
@Composable
fun HomeScreen(context: Context, modifier: Modifier = Modifier, navController: NavHostController) {
    val records = RecordReader.readRecords(context)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
        content = { innerPadding ->
            Column(modifier = modifier.padding(innerPadding)) {
                MusicRecordList(musicRecords = records.values.toList()) {
                    navController.navigate("details/${it.id}")
                }
            }
        }
    )
}

@Composable
fun DetailsScreen(musicRecord: MusicRecord, modifier: Modifier = Modifier, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.details_screen_name)) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                )
        },
        content = { innerPadding ->
            Column(modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
            ) {
                MusicRecordInfo(musicRecord = musicRecord)
            }
        }
    )

}