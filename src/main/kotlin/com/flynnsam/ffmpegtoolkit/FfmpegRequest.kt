package com.flynnsam.ffmpegtoolkit

import com.flynnsam.ffmpegtoolkit.filters.FfmpegFilter
import java.io.File

/**
 * Class for holding all of the options of an ffmpage job
 */
class FfmpegRequest(var inputFile : File) {

    companion object {
        const val DEFAULT_FILE_NAME = "out"
    }

    var withOverwrite: Boolean = false
    var startTime: String = "0"
    var duration: String? = null
    var frameRate: Double? = null
    val filters: MutableList<FfmpegFilter> = mutableListOf()
    var outputDir: File? = null
        get() = when {
            field != null -> field
            else -> File(this.inputFile.parent)
        }
    var outputFileName: String = DEFAULT_FILE_NAME
}