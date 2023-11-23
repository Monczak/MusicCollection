package edu.pwr.s266867.musiccollection.util

import android.annotation.SuppressLint
import android.content.Context
import edu.pwr.s266867.musiccollection.R
import edu.pwr.s266867.musiccollection.musicdata.MusicRecord
import edu.pwr.s266867.musiccollection.musicdata.MusicTrack
import org.yaml.snakeyaml.Yaml

object RecordReader {
    private lateinit var records: Map<Int, MusicRecord>

    fun readRecords(context: Context): Map<Int, MusicRecord> {
        records = readRecordsFromYaml(context)
        return records
    }

    fun getRecordById(id: Int): MusicRecord? {
        return records[id]
    }

    @SuppressLint("DiscouragedApi")
    fun readRecordsFromYaml(context: Context): Map<Int, MusicRecord> {
        val yamlFile = context.resources.openRawResource(R.raw.record_data)
            .bufferedReader().use { it.readText() }

        val yaml = Yaml()
        val records = mutableMapOf<Int, MusicRecord>()

        var id = 0
        yaml.loadAll(yamlFile).forEach { data ->
            val record = parseMusicRecordData(data, context, id)
            records[id] = record
            id++
        }

        return records
    }

    private fun parseMusicRecordData(
        data: Any?,
        context: Context,
        id: Int
    ): MusicRecord {
        val recordData = data as Map<*, *>
        val title = recordData["title"] as String
        val artist = recordData["artist"] as String
        val genre = recordData["genre"] as String
        val year = recordData["year"] as Int
        val coverResId = recordData["coverResId"] as String
        val description = recordData["description"] as String

        val tracks = mutableListOf<MusicTrack>()
        (recordData["tracks"] as List<*>).forEach { tData ->
            val track = parseMusicTrackData(tData)
            tracks.add(track)
        }

        return MusicRecord(
            id,
            title,
            artist,
            genre,
            year,
            context.resources.getIdentifier(coverResId, "drawable", context.packageName),
            description,
            tracks
        )
    }

    private fun parseMusicTrackData(tData: Any?): MusicTrack {
        val trackData = tData as Map<*, *>
        val name = trackData["name"] as String
        val length = trackData["length"] as String

        val artistsList = mutableListOf<String>()
        (trackData["artists"] as List<*>).forEach { artist -> artistsList.add(artist as String) }

        return MusicTrack(name, Util.parseTimestamp(length), artistsList)
    }
}