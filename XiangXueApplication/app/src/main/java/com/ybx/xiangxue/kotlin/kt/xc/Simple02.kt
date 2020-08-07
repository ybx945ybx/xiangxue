package com.ybx.xiangxue.kotlin.kt.xc

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Author 55HAITAO
 * @Date 2020/7/6 4:15 PM
 */

suspend fun main() {
    val job = GlobalScope.launch {
        repeat(1000) {
            println("11111   $it")
            delay(20)
        }
    }


    delay(1000)
    println("aaaaaa")

    job.cancel()
}