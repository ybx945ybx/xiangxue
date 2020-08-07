package com.ybx.xiangxue.kotlin.kt

/**
 * @Author 55HAITAO
 * @Date 2020/7/6 1:28 PM
 */
fun main() {

}

fun <T, R> T.myRun(boolean: Boolean, mm: (Boolean) -> R): T {
    mm(boolean)
    return this
}
