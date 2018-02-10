package com.flynnsam.ffmpegtoolkit

import java.io.File

class FfmpegRequestBuilder(val input : String, val outputFormat : OutputFormat) {

    val inputFile : File

    val request = FfmpegRequest()

    init {
        inputFile = File(input)
        if (!inputFile.exists())
            throw IllegalArgumentException("Input file $input doesn't exist")
        request.inputFile = inputFile
    }

    fun run() {
        outputFormat.invoker.invokeFfmpeg(request)
    }
}