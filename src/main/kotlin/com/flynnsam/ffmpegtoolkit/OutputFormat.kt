package com.flynnsam.ffmpegtoolkit

import com.flynnsam.ffmpegtoolkit.invokers.FfmpegInvoker
import com.flynnsam.ffmpegtoolkit.invokers.GifFfmpegInvoker
import com.flynnsam.ffmpegtoolkit.invokers.MP4FfmpegInvoker

/**
 * Available output formats.
 */
enum class OutputFormat(val invoker: FfmpegInvoker) {

    MP4(MP4FfmpegInvoker(Runtime.getRuntime())),
    GIF(GifFfmpegInvoker(Runtime.getRuntime()))
}