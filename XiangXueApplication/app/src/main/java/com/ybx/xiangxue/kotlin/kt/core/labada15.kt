package com.ybx.xiangxue.kotlin.kt.core

/**
 * @Author 55HAITAO
 * @Date 2020/7/6 4:52 PM
 */

// lambada 15种写法

fun main() {

    // :  无实现
    // :  有实现
    // =  有实现

    // 没有实现的
    var m01: () -> Unit

    var m02: (Int) -> String

    var m03: (Int, Int, String?) -> String

    var m04: (Int, Int) -> String


    // 有实现的
    var m09: () -> Unit = {
        println("m09")
    }
    m09()

    var m10: () -> String = {
        "我是m10的返回值呀"
    }
    println("m10 ${m10()}")

    var m11: (Int) -> String = {
        when (it) {
            1 -> "我是1"
            2 -> "我是2"
            else -> "我不是"
        }
    }
    println("m11 ${m11(11)}")

    var m12: (Int, Int, String) -> String = { n1, n2, s3 ->
        var result = "我是无结果"
        if (!s3.isNullOrEmpty()) {
            result = "我是结果 ${n1 + n2}"
        }
        result
    }
    println("m12 : ${m12(3, 3, " ")}")


    // 有=的是有实现的   能定义 能调用
    var m05 = { n: Int -> Unit }
    var m06 = { n: Int -> 8 + n }

    println("m06 ${m06(4)}")

    var m07 = { n1: Int, n2: Int -> n1 + n2 }
    println("m07 ${m07(3, 4)}")

    var m08 = { n1: Int, n2: Int -> "我是两数的和 ${n1 + n2}" }
    println("m08 ${m08(6, 9)}")

//    fun m06() = { n1: Int, s1: String -> Unit }

//    fun m06() = { n1: Int, s1: String -> String() }
//
//
//    m03()

//    m05(8)


    var m13 = {}
    var m14 = { it: Boolean -> if (it) "是对的" else "是错的" }
    var m15 = { b: Boolean, int: Int -> if (b) 100 + int else "没结果" }

    println("m15: ${m15(false, 10)}")

//    loginService("ybx945", "123455") { name, pwd ->
//        println("用户名 $name , 密码 $pwd 登录了 ")
//    }

    loginService2("ybx", "123456") {
        if (it) {

            println("登录成功了")
        } else {
            println("登录失败了")

        }
    }

    val  result = loginTest("ybx","123456"){name,pwd->
        return@loginTest name == "ybx" && pwd == "123456"
    }
    println("logtest $result")
}


typealias Request = (String, String) -> Unit

fun loginService(name: String, pwd: String, req: Request) {
    req(name, pwd)
}

fun loginService2(name: String, pwd: String, respon: (Boolean) -> Unit) {
    if (name == "ybx" && pwd == "123456") {
        respon(true)
    } else {
        respon(false)
    }
}

fun loginTest(name: String, pwd: String, mm: (String, String) -> Boolean): Int {
    val result = mm(name, pwd)
    println("mm $result")

    return if (result) 666 else 0
}