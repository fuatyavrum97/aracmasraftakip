package com.fuat.aracmasraftakip.utils

import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object LogWrapper {
    private const val LOG_FILE_NAME = "AracMasrafTakip_FullLog.txt"
    private const val MAX_FILE_SIZE = 3 * 1024 * 1024 // 3 MB

    fun d(tag: String, message: String) {
        writeLongMessage("D", tag, message)
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        val fullMessage = message +
                throwable?.let { "\n${Log.getStackTraceString(it)}" }.orEmpty()
        writeLongMessage("E", tag, fullMessage)
    }

    fun w(tag: String, message: String) {
        writeLongMessage("W", tag, message)
    }

    /** 4000 karakterden uzun logları parçalayarak logcat’e ve dosyaya yazar */
    private fun writeLongMessage(level: String, tag: String, message: String) {
        val maxLogLength = 4000
        var start = 0
        while (start < message.length) {
            val end = (start + maxLogLength).coerceAtMost(message.length)
            val chunk = message.substring(start, end)

            when (level) {
                "D" -> Log.d(tag, chunk)
                "E" -> Log.e(tag, chunk)
                "W" -> Log.w(tag, chunk)
                else -> Log.i(tag, chunk)
            }

            writeToFile(level, tag, chunk)
            start = end
        }
    }

    private fun writeToFile(level: String, tag: String, message: String) {
        try {
            val file = getLogFile()
            val timestamp = getCurrentDateTime()
            val pid = android.os.Process.myPid()
            val line = "$timestamp $pid $level $tag: $message\n"

            FileOutputStream(file, true).use { it.write(line.toByteArray()) }

            if (file.length() >= MAX_FILE_SIZE) {
                file.writeText("")
            }
        } catch (ex: Exception) {
            Log.e("LogWrapper", "Yazma hatası: ${ex.message}", ex)
        }
    }

    private fun getLogFile(): File {
        val ctx = ContextProvider.getContext()
        val dir = File(ctx.filesDir, "logs").apply { if (!exists()) mkdirs() }
        return File(dir, LOG_FILE_NAME)
    }

    private fun getCurrentDateTime(): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
            .format(Date())
    }
}
