package edu.pwr.s266867.musiccollection

import android.content.Context
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.pwr.s266867.musiccollection.composables.DetailsScreen
import edu.pwr.s266867.musiccollection.composables.HomeScreen
import edu.pwr.s266867.musiccollection.composables.MusicRecordList
import edu.pwr.s266867.musiccollection.musicdata.MusicRecord
import edu.pwr.s266867.musiccollection.ui.theme.MusicCollectionTheme
import edu.pwr.s266867.musiccollection.util.RecordReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RecordReader.readRecords(this)

        setContent {
            MusicCollectionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MusicCollectionNavHost(context = this)
                }
            }
        }
    }
}

@Composable
fun MusicCollectionNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home",
    context: Context
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") {
            HomeScreen(context = context, navController = navController)
        }
        composable(
            "details/{recordId}",
            arguments = listOf(navArgument("recordId") { type = NavType.IntType })
        ) { backStackEntry ->
            val record = RecordReader.getRecordById(backStackEntry.arguments?.getInt("recordId") ?: -1)
            if (record != null)
                DetailsScreen(musicRecord = record)
        }
    }

}
