package com.flynnsam.ffmpegtoolkit.invokers

import com.flynnsam.ffmpegtoolkit.FfmpegRequest

abstract class AbstractFfmpegInvoker : FfmpegInvoker {

    override fun invokeFfmpeg(ffmpegRequest: FfmpegRequest) {

        ffmpegRequest.filters.forEach { filter -> filter.setup() }

        doSystemInvocations(ffmpegRequest)

        ffmpegRequest.filters.forEach { filter -> filter.cleanUp() }

    }

    abstract fun doSystemInvocations(ffmpegRequest: FfmpegRequest)
}