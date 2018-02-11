package com.flynnsam.ffmpegtoolkit

import com.flynnsam.ffmpegtoolkit.filters.FfmpegFilter
import java.io.File

class FfmpegRequest(var inputFile : File) {

    var outputFile : File? = null
    var withOverwrite : Boolean = false
    var startTime : String = "0"
    var duration : String? = null
    var frameRate : Double? = null
    val filters : MutableList<FfmpegFilter> = mutableListOf()

}