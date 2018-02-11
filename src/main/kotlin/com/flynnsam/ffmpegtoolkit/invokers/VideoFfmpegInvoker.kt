package com.flynnsam.ffmpegtoolkit.invokers

import com.flynnsam.ffmpegtoolkit.FfmpegRequest

class VideoFfmpegInvoker : AbstractFfmpegInvoker() {

    override val outputExtension: String = "mp4"

    override fun doSystemInvocations(ffmpegRequest: FfmpegRequest) {

        val commandLine = StringBuilder(properties[FFMPEG_BIN_PATH_PROPERTY_REF].toString())

        addNoSubtitles(commandLine)
        addOverwrite(ffmpegRequest, commandLine)
        addStartTime(ffmpegRequest, commandLine)
        addDuration(ffmpegRequest, commandLine)
        addInputFile(ffmpegRequest, commandLine)
        addSimpleVideoFilter(ffmpegRequest, commandLine)
        addFrameRate(ffmpegRequest, commandLine)
        addOutputFile(ffmpegRequest, commandLine)

        Runtime.getRuntime().exec(commandLine.toString())
    }
}