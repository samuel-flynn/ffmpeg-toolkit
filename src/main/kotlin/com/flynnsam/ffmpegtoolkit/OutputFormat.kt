package com.flynnsam.ffmpegtoolkit

import com.flynnsam.ffmpegtoolkit.invokers.FfmpegInvoker
import com.flynnsam.ffmpegtoolkit.invokers.GifFfmpegInvoker
import com.flynnsam.ffmpegtoolkit.invokers.VideoFfmpegInvoker

enum class OutputFormat(val invoker: FfmpegInvoker) {

    MP4(VideoFfmpegInvoker(Runtime.getRuntime())),
    GIF(GifFfmpegInvoker(Runtime.getRuntime()))
}