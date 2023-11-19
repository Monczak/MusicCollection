package edu.pwr.s266867.musiccollection.util

import android.annotation.SuppressLint
import android.content.Context
import edu.pwr.s266867.musiccollection.R
import edu.pwr.s266867.musiccollection.musicdata.MusicRecord
import edu.pwr.s266867.musiccollection.musicdata.MusicTrack
import org.yaml.snakeyaml.Yaml

object RecordReader {
    @SuppressLint("DiscouragedApi")
    fun readRecordsFromYaml(context: Context): List<MusicRecord> {
        val yamlFile = context.resources.openRawResource(R.raw.record_data)
            .bufferedReader().use { it.readText() }

        val yaml = Yaml()
        val records = mutableListOf<MusicRecord>()

        yaml.loadAll(yamlFile).forEach { data ->
            val record = parseMusicRecordData(data, context)
            records.add(record)
        }

        return records
    }

    private fun parseMusicRecordData(
        data: Any?,
        context: Context
    ): MusicRecord {
        val recordData = data as Map<*, *>
        val title = recordData["title"] as String
        val artist = recordData["artist"] as String
        val genre = recordData["genre"] as String
        val year = recordData["year"] as Int
        val coverResId = recordData["coverResId"] as String

        val tracks = mutableListOf<MusicTrack>()
        (recordData["tracks"] as List<*>).forEach { tData ->
            val track = parseMusicTrackData(tData)
            tracks.add(track)
        }

        return MusicRecord(
            title,
            artist,
            genre,
            year,
            context.resources.getIdentifier(coverResId, "drawable", context.packageName),
            tracks
        )
    }

    private fun parseMusicTrackData(tData: Any?): MusicTrack {
        val trackData = tData as Map<*, *>
        val name = trackData["name"] as String
        val length = trackData["length"] as String

        val artistsList = mutableListOf<String>()
        (trackData["artists"] as List<*>).forEach { artist -> artistsList.add(artist as String) }

        return MusicTrack(name, Util.parseLengthString(length), artistsList)
    }
}