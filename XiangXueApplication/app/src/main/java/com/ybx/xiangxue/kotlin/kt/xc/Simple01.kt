package com.ybx.xiangxue.kotlin.kt.xc

import kotlinx.coroutines.*

/**
 * @Author 55HAITAO
 * @Date 2020/7/6 3:22 PM
 */
fun main() = runBlocking {

    // 类似于守护进程  main结束就不打印了
//    GlobalScope.launch {
//        delay(1000)
//        println("11111111111")
//    }

    // main结束还是会打印
//    withContext(Dispatchers.IO){
//        delay(1000)
//        println("11111111111")
//    }

    val job = launch {

    }
    job.cancel()        // 有一点点时间差
    job.cancelAndJoin() //  一点一点时间差都不允许

    val d = async { "dddd" }
    d.await()

    println("AAAA")

    delay(300)

    println("BBBB")
}