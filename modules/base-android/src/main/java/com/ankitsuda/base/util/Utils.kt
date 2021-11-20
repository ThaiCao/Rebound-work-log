package com.ankitsuda.base.util

fun Float.cmprs(min: Float, max: Float) = min + (max - min) * this
fun Float.cmprs(min: Int, max: Int) = this.cmprs(min.toFloat(), max.toFloat())
fun Int.cmprs(min: Int, max: Int) = this.toFloat().cmprs(min.toFloat(), max.toFloat())
fun Int.cmprs(min: Float, max: Float) = this.toFloat().cmprs(min, max)

val NONE_WORKOUT_ID = (-1).toLong()

class Utils {
    companion object {


    }
}