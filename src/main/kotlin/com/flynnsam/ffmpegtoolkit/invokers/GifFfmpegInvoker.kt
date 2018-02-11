package com.flynnsam.ffmpegtoolkit.invokers

import com.flynnsam.ffmpegtoolkit.FfmpegRequest
import java.io.File

class GifFfmpegInvoker : AbstractFfmpegInvoker() {

    companion object {
        const val NO_AUDIO_PARAMETER = "-an"
    }

    override val outputExtension: String = "gif"

    override fun doSystemInvocations(ffmpegRequest: FfmpegRequest) {

        val paletteFile = createPalette(ffmpegRequest)
        createGif(ffmpegRequest)
        cleanUpPalette(ffmpegRequest)
    }

    private fun addNoAudio(stringBuilder: StringBuilder) {
        stringBuilder.append(" $NO_AUDIO_PARAMETER ")
    }

    private fun createPalette(ffmpegRequest: FfmpegRequest) : File {

        val palettaFile = File()

        val paletteGenCommand = getGifBasics(ffmpegRequest)

        addSimpleVideoFilter(ffmpegRequest, paletteGenCommand)
        paletteGenCommand.append(" $SIMPLE_VIDEO_FILTER_PARAMETER \"palettegen=stats_mode=diff\"")
        addOutputFile(ffmpegRequest, palettaFile)

        Runtime.getRuntime().exec(paletteGenCommand.toString())
    }

    private fun createGif(ffmpegRequest: FfmpegRequest) {

    }

    private fun cleanUpPalette(paletteFile: File) {

        if (paletteFile.exists()) {
            paletteFile.delete()
        }
    }

    private fun getGifBasics(ffmpegRequest: FfmpegRequest) : StringBuilder {
        val commandLine = StringBuilder(properties[FFMPEG_BIN_PATH_PROPERTY_REF].toString())

        addNoSubtitles(commandLine)
        addOverwrite(ffmpegRequest, commandLine)
        addStartTime(ffmpegRequest, commandLine)
        addDuration(ffmpegRequest, commandLine)
        addFrameRate(ffmpegRequest, commandLine)
        addInputFile(ffmpegRequest, commandLine)
    }
}