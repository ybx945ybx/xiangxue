package com.ybx.xiangxue.kotlin.kt.core

import kotlinx.coroutines.delay

/**
 * @Author 55HAITAO
 * @Date 2020/7/6 7:34 PM
 */


val names = "ybx"
val sexs = "m"

fun commons() {}


fun main() {
    names.run {
        length
    }
    names.let { }


    val r = names.myRun {
        length
    }

    names.myLet {
        it.length
    }

    val re = myWith(names) {
        length
    }

    names.myApply {
        it.length
    }.myApply { }.length



    ktrun(true, "") {
        doCounts(9) {
            println("计数中 $it")
        }
    }
}

fun <T, R> T.myRun(mm: T.() -> R): R {
    return mm()
}

fun <T, R> T.myLet(mm: (T) -> R): R {

    return mm(this)
}

fun <T, R> myWith(input: T, mm: T.() -> R): R {

    return input.mm()
}

fun <T> T.myApply(mm: (T) -> Unit): T {
    mm(this)
    return this
}

fun ktrun(
    start: Boolean,
    name: String,
    myRunAction: () -> Unit
): Thread {
    val thread = object : Thread() {
        override fun run() {
            super.run()
            myRunAction()

        }
    }

    if (start) thread.start()

    return thread
}

fun doCounts(counts: Int, mm: (Int) -> Unit) {
    for (count in 0 until counts) {
        mm(count)
    }
}