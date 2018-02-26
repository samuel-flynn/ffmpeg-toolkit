package com.flynnsam.ffmpegtoolkit.expression

/**
 * Interface for expressions, which are used by filters. Expressions can be
 * <ul>
 *     <li>Simple numerical values. e.g. <pre>4.5</pre></li>
 *     <li>Mathematical operations. e.g. <pre>4.5 * 2</pre></li>
 *     <li>Values involving special symbols. e.g <pre>INPUT_VIDEO_WIDTH / 2</pre></li>
 * </ul>
 */
interface Expression {

    fun renderToString() : String
}