package com.flynnsam.ffmpegtoolkit.invokers

import com.flynnsam.ffmpegtoolkit.FfmpegRequest

interface FfmpegInvoker {

    fun invokeFfmpeg(ffmpegRequest : FfmpegRequest)
}