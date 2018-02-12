package com.flynnsam.ffmpegtoolkit

import com.flynnsam.ffmpegtoolkit.filters.FfmpegFilter
import java.io.File

class FfmpegRequestBuilder(input : String, private val outputFormat : OutputFormat) {

    private val request : FfmpegRequest

    init {
        val inputFile = File(input)
        if (!inputFile.exists())
            throw IllegalArgumentException("Input file $input doesn't exist")
        request = FfmpegRequest(inputFile)
    }

    fun withStartTime(seconds : Double) : FfmpegRequestBuilder {
        request.startTime = seconds.toString()
        return this
    }

    fun withStartTime(timestamp : String) : FfmpegRequestBuilder {
        request.startTime = timestamp
        return this
    }

    fun withOverwrite(overwrite : Boolean) : FfmpegRequestBuilder {
        request.withOverwrite = overwrite
        return this
    }

    fun withDuration(seconds : Double) : FfmpegRequestBuilder {
        request.duration = seconds.toString()
        return this
    }

    fun withDuration(timestamp : String) : FfmpegRequestBuilder {
        request.duration = timestamp
        return this
    }

    fun withOutputDir(dirPath : String) : FfmpegRequestBuilder {
        request.outputDir = File(dirPath)
        return this
    }

    fun withOutputFileName(fileName : String) : FfmpegRequestBuilder {
        request.outputFileName = fileName
        return this
    }

    fun withFilter(filter : FfmpegFilter) : FfmpegRequestBuilder {
        request.filters.add(filter)
        return this
    }

    fun withFrameRate(frameRate : Double) : FfmpegRequestBuilder {
        request.frameRate = frameRate
        return this
    }

    fun run() {
        outputFormat.invoker.invokeFfmpeg(request)
    }
}