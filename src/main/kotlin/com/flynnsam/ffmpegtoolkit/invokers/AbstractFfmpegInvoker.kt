package com.flynnsam.ffmpegtoolkit.invokers

import com.flynnsam.ffmpegtoolkit.FfmpegRequest
import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 * Abstract {@link FfmpegInvoker} that defines constants and common functionality for concrete invokers.
 */
abstract class AbstractFfmpegInvoker(protected val executor: Runtime) : FfmpegInvoker {

    companion object {
        const val FFMPEG_BIN_PATH_PROPERTY_REF = "ffmpeg.bin.path"

        const val OVERWRITE_PARAMETER = "-y"
        const val NO_SUBTITLES_PARAMETER = "-sn"
        const val START_TIME_PARAMETER = "-ss"
        const val DURATION_PARAMETER = "-t"
        const val INPUT_FILE_PARAMETER = "-i"
        const val SIMPLE_VIDEO_FILTER_PARAMETER = "-vf"
        const val LIB_AV_FILTER_PARAMETER = "-lavfi"
        const val FRAME_RATE_PARAMETER = "-r"
    }

    protected val properties = Properties()

    init {
        val envValue = System.getenv(FFMPEG_BIN_PATH_PROPERTY_REF)
        if (envValue != null) {
            properties[FFMPEG_BIN_PATH_PROPERTY_REF] = envValue
        } else {
            properties.load(FileInputStream("application.properties"))
        }
    }

    override fun invokeFfmpeg(ffmpegRequest: FfmpegRequest) {

        ffmpegRequest.filters.forEach { filter -> filter.setup() }

        doExecutorInvocations(ffmpegRequest)

        ffmpegRequest.filters.forEach { filter -> filter.cleanUp() }

    }

    /**
     * Do the executor invocations required by the format
     * @param ffmpegRequest The options built by the user
     */
    abstract fun doExecutorInvocations(ffmpegRequest: FfmpegRequest)

    /**
     * The file extension the format uses
     */
    abstract val outputExtension: String

    /**
     * Utility function to add the overwrite parameter
     */
    protected fun addOverwrite(ffmpegRequest: FfmpegRequest, stringBuilder: StringBuilder) {
        if (ffmpegRequest.withOverwrite) {
            stringBuilder.append(" $OVERWRITE_PARAMETER ")
        }
    }

    /**
     * Utility function to add the "no subtitles" parameter
     */
    protected fun addNoSubtitles(stringBuilder: StringBuilder) {
        stringBuilder.append(" $NO_SUBTITLES_PARAMETER ")
    }

    /**
     * Utility function to add the start time parameter
     */
    protected fun addStartTime(ffmpegRequest: FfmpegRequest, stringBuilder: StringBuilder) {
        stringBuilder.append(" $START_TIME_PARAMETER ${ffmpegRequest.startTime} ")
    }

    /**
     * Utility function to add the duration parameter
     */
    protected fun addDuration(ffmpegRequest: FfmpegRequest, stringBuilder: StringBuilder) {
        if (ffmpegRequest.duration != null) {
            stringBuilder.append(" $DURATION_PARAMETER $ffmpegRequest.duration ")
        }
    }

    /**
     * Utility function to add the input file parameter
     */
    protected fun addInputFile(ffmpegRequest: FfmpegRequest, stringBuilder: StringBuilder) {
        stringBuilder.append(" $INPUT_FILE_PARAMETER ${ffmpegRequest.inputFile.absolutePath} ")
    }

    /**
     * Utility function to add a simple filter parameter with all of the filters in the options
     */
    protected fun addSimpleVideoFilter(ffmpegRequest: FfmpegRequest, stringBuilder: StringBuilder) {
        if (ffmpegRequest.filters.size > 0) {
            val filtersString = ffmpegRequest.filters.map { filter -> filter.getFilterString() }.joinToString(",")
            stringBuilder.append(" $SIMPLE_VIDEO_FILTER_PARAMETER \"$filtersString\" ")
        }
    }

    /**
     * Utility function to add a LibAV filter parameter with all of the filters in the options
     */
    protected fun addLibAVFilter(ffmpegRequest: FfmpegRequest, stringBuilder: StringBuilder) {
        if (ffmpegRequest.filters.size > 0) {
            val filtersString = ffmpegRequest.filters.map { filter -> filter.getFilterString() }.joinToString(",")
            stringBuilder.append(" $LIB_AV_FILTER_PARAMETER \"$filtersString\" ")
        }
    }

    /**
     * Utility function to add the frame rate parameter
     */
    protected fun addFrameRate(ffmpegRequest: FfmpegRequest, stringBuilder: StringBuilder) {
        if (ffmpegRequest.frameRate != null) {
            stringBuilder.append(" $FRAME_RATE_PARAMETER ${ffmpegRequest.frameRate.toString()} ")
        }
    }

    /**
     * Utility function to add the output file parameter
     */
    protected fun addOutputFile(ffmpegRequest: FfmpegRequest, stringBuilder: StringBuilder) {
        var fileNameToUse: String

        fileNameToUse = ffmpegRequest.outputFileName as String
        if (!fileNameToUse.endsWith(".${outputExtension}", true)) {
            fileNameToUse = "$fileNameToUse.$outputExtension"
        }

        stringBuilder.append(" ${ffmpegRequest.outputDir?.absolutePath}${File.separator}$fileNameToUse ")
    }
}