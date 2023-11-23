package edu.pwr.s266867.musiccollection.util

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import kotlin.math.floor

object Util {
    fun parseTimestamp(lengthStr: String): Int {
        val parts = lengthStr.split(":")

        if (parts.size == 2) {
            try {
                val minutes = parts[0].toInt()
                val seconds = parts[1].toInt()

                return minutes * 60 + seconds
            }
            catch (e: NumberFormatException) {
                throw IllegalArgumentException("Improper timestamp format")
            }
        }
        else throw IllegalArgumentException("Improper timestamp format")
    }

    fun getTimestamp(lengthSeconds: Int): String {
        val seconds: Int = lengthSeconds % 60
        val minutes: Int = floor(lengthSeconds / 60.0).toInt()

        return "%d:%02d".format(minutes, seconds)
    }
}