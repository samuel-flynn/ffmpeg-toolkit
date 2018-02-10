package com.flynnsam.ffmpegtoolkit.filters

interface FfmpegFilter {

    fun setup()

    fun getFilterString()

    fun cleanUp()
}