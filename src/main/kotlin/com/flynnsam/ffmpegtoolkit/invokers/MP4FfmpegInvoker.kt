package com.flynnsam.ffmpegtoolkit.invokers

import com.flynnsam.ffmpegtoolkit.FfmpegRequest

/**
 * Class for invoking ffmpeg to create videos as MP4s
 */
class MP4FfmpegInvoker(executor : Runtime) : AbstractFfmpegInvoker(executor) {

    override val outputExtension: String = "mp4"

    override fun doExecutorInvocations(ffmpegRequest: FfmpegRequest) {

        val commandLine = StringBuilder(properties[FFMPEG_BIN_PATH_PROPERTY_REF].toString())

        addNoSubtitles(commandLine)
        addOverwrite(ffmpegRequest, commandLine)
        addStartTime(ffmpegRequest, commandLine)
        addDuration(ffmpegRequest, commandLine)
        addInputFile(ffmpegRequest, commandLine)
        addSimpleVideoFilter(ffmpegRequest, commandLine)
        addFrameRate(ffmpegRequest, commandLine)
        addOutputFile(ffmpegRequest, commandLine)

        executor.exec(commandLine.toString())
    }
}