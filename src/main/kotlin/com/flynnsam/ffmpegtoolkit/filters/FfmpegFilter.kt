package com.flynnsam.ffmpegtoolkit.filters

/**
 * Interface of an ffmpeg filter.
 */
interface FfmpegFilter {

    /**
     * Perform any setup operations needed by this filter
     */
    fun setup()

    /**
     * Get the actual filter string to be put into the ffmpeg command line command
     */
    fun getFilterString() : String

    /**
     * Perform any cleanup operations needed by this filter
     */
    fun cleanUp()
}