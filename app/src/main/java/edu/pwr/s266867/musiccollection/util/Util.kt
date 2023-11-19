package edu.pwr.s266867.musiccollection.util

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

object Util {
    fun parseLengthString(lengthStr: String): Int {
        val parts = lengthStr.split(":")

        if (parts.size == 2) {
            try {
                val minutes = parts[0].toInt()
                val seconds = parts[1].toInt()

                return minutes * 60 + seconds
            }
            catch (e: NumberFormatException) {
                throw IllegalArgumentException("Improper length string format")
            }
        }
        else throw IllegalArgumentException("Improper length string format")
    }
}