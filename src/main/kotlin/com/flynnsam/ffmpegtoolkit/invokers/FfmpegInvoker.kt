package com.flynnsam.ffmpegtoolkit.invokers

import com.flynnsam.ffmpegtoolkit.FfmpegRequest

/**
 * Interface for processes that actually invoke ffmpeg with a set of options. This is done to set the output format
 * and for configuring options specific to that format (e.g. GIFs need a palette generation step).
 */
interface FfmpegInvoker {

    /**
     * Invoke ffmpeg with the options set by the user.
     * @param ffmpegRequest The request options built by the user
     */
    fun invokeFfmpeg(ffmpegRequest : FfmpegRequest)
}