package com.flynnsam.ffmpegtoolkit.expression

/**
 * Mathematical operators used by {@link Expression}s.
 * @param stringRepresentation The string representation of this operator
 */
enum class MathematicalOperator(val stringRepresentation: String) {

    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/")
}