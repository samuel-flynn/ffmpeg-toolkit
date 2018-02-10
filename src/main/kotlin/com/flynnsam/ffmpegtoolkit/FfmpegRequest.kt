package com.flynnsam.ffmpegtoolkit

import com.flynnsam.ffmpegtoolkit.filters.FfmpegFilter
import java.io.File

data class FfmpegRequest(
        var inputFile : File,
        var outputFile : File,
        var withOverwrite : Boolean,
        var startTime : String,
        var duration : String,
        var filters : Array<FfmpegFilter>)