package com.flynnsam.ffmpegtoolkit.invokers

import com.flynnsam.ffmpegtoolkit.FfmpegRequest
import java.io.File

/**
 * Class for invoking ffmpeg to create videos as animated GIFs
 */
class GifFfmpegInvoker(executor : Runtime) : AbstractFfmpegInvoker(executor) {

    companion object {
        const val NO_AUDIO_PARAMETER = "-an"
    }

    override val outputExtension: String = "gif"

    override fun doExecutorInvocations(ffmpegRequest: FfmpegRequest) {

        val paletteFile = createPalette(ffmpegRequest)
        createGif(ffmpegRequest, paletteFile)
        cleanUpPalette(paletteFile)
    }

    private fun addNoAudio(stringBuilder: StringBuilder) {
        stringBuilder.append(" $NO_AUDIO_PARAMETER ")
    }

    private fun createPalette(ffmpegRequest: FfmpegRequest) : File {

        val paletteFile = File("${ffmpegRequest.outputDir?.absolutePath}${File.separator}gif_palette.png")

        val paletteGenCommand = getGifBasics(ffmpegRequest)

        addSimpleVideoFilter(ffmpegRequest, paletteGenCommand)
        paletteGenCommand.append(" $SIMPLE_VIDEO_FILTER_PARAMETER \"palettegen=stats_mode=diff\"")
        addOutputFile(ffmpegRequest, paletteGenCommand)

        executor.exec(paletteGenCommand.toString())

        return paletteFile
    }

    private fun createGif(ffmpegRequest: FfmpegRequest, paletteFile: File) {

        val gifGenCommand = getGifBasics(ffmpegRequest)

        addNoAudio(gifGenCommand)
        gifGenCommand.append(" $INPUT_FILE_PARAMETER \"${paletteFile.absolutePath}\"")
        addLibAVFilter(ffmpegRequest, gifGenCommand)
        gifGenCommand.append(" $LIB_AV_FILTER_PARAMETER \" [x]; [x][1:v] paletteuse\"")
        addOutputFile(ffmpegRequest, gifGenCommand)

        executor.exec(gifGenCommand.toString())
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

        return commandLine
    }
}