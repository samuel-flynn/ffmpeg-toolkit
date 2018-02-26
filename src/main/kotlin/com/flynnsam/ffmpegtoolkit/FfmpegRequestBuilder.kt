package com.flynnsam.ffmpegtoolkit

import com.flynnsam.ffmpegtoolkit.filters.FfmpegFilter
import java.io.File

/**
 * The main builder class for ffmpeg-tools. This builder handles all the configuration of your ffmpeg job.
 * @param input The path to your input file. For example {@code C:\Users\me\Videos\SWAP.avi}
 * @param outputFormat The format that you will be rendering your video to
 */
class FfmpegRequestBuilder(input : String, private val outputFormat : OutputFormat) {

    private val request : FfmpegRequest

    init {
        val inputFile = File(input)
        if (!inputFile.exists())
            throw IllegalArgumentException("Input file $input doesn't exist")
        request = FfmpegRequest(inputFile)
    }

    /**
     * Add a start time to your job. Your output rendering will occur {@code seconds} seconds into your input
     * @param seconds The number of seconds into your input to start rendering
     * @return this
     */
    fun withStartTime(seconds : Double) : FfmpegRequestBuilder {
        request.startTime = seconds.toString()
        return this
    }

    /**
     * Add a start time to your job. Your output rendering will occur {@code timestamp} into your input.
     * {@code timestamp} can be of the format {@code ss}, {@code mm:ss}, or {@code hh:mm:ss}.
     * @param seconds The number of seconds into your input to start rendering
     * @return this
     */
    fun withStartTime(timestamp : String) : FfmpegRequestBuilder {
        request.startTime = timestamp
        return this
    }

    /**
     * Set whether or not the job should automatically overwrite any existing files.
     * @param overwrite true if this job should overwrite any existing files, false otherwise
     */
    fun withOverwrite(overwrite : Boolean) : FfmpegRequestBuilder {
        request.withOverwrite = overwrite
        return this
    }

    /**
     * Add a duration to your job. Your output rendering will be {@code seconds} seconds long. Not specifying a duration
     * (i.e. never calling this function) will mean that your job will continue rendering until it reaches the end of
     * the input file.
     * @param seconds The number of seconds into your input to start rendering
     * @return this
     */
    fun withDuration(seconds : Double) : FfmpegRequestBuilder {
        request.duration = seconds.toString()
        return this
    }

    /**
     * Add a duration to your job. Your output rendering will be {@code timestamp} long. Not specifying a duration
     * (i.e. never calling this function) will mean that your job will continue rendering until it reaches the end of
     * the input file.
     * {@code timestamp} can be of the format {@code ss}, {@code mm:ss}, or {@code hh:mm:ss}.
     * @param seconds The number of seconds into your input to start rendering
     * @return this
     */
    fun withDuration(timestamp : String) : FfmpegRequestBuilder {
        request.duration = timestamp
        return this
    }

    /**
     * Add an output directory to your job. Not specifying a output directory (i.e. never calling this function) will
     * mean that the output directory will be the same directory as the input file.
     * @param dirPath The directory to put any output files
     * @return this
     */
    fun withOutputDir(dirPath : String) : FfmpegRequestBuilder {
        request.outputDir = File(dirPath)
        return this
    }

    /**
     * Add an output file name for your job. Not specifying a output directory (i.e. never calling this function) will
     * mean that the output filename will be {@code out}
     * @param fileName the file name to use
     * @return this
     */
    fun withOutputFileName(fileName : String) : FfmpegRequestBuilder {
        request.outputFileName = fileName
        return this
    }

    /**
     * Add a filter to this job. Filters are applied in the order in which they're added.
     * @param filter the filter to add
     * @return this
     */
    fun withFilter(filter : FfmpegFilter) : FfmpegRequestBuilder {
        request.filters.add(filter)
        return this
    }

    /**
     * Set a frame rate on the output.
     * @param frameRate the frame rate to use
     * @return this
     */
    fun withFrameRate(frameRate : Double) : FfmpegRequestBuilder {
        request.frameRate = frameRate
        return this
    }

    /**
     * Run the job with settings that have been previously applied.
     */
    fun run() {
        outputFormat.invoker.invokeFfmpeg(request)
    }
}