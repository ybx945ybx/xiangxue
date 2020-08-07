package com.ybx.xiangxue.kotlin.kt

import com.ybx.xiangxue.kotlin.java.JavaCallback
import com.ybx.xiangxue.kotlin.java.JavaManger

/**
 * @Author 55HAITAO
 * @Date 2020/7/2 11:09 AM
 *
 *
 * kt核心   协程  高阶   闭包
 * 在派生和objrct的时候  尽量使用object表示单例 kt内部对object进行了很多处理    需要传参的话就用派生
 */
class Client {

}

fun main() {

    //  kt  使用java的callback
    // 第一种
//    JavaManger().setCallBack(JavaCallback { aaa -> println(aaa) })


//    第二种
//    JavaManger().setCallBack { println(it) }


//    第三种
//    val callback = JavaCallback { println(it) }
//    JavaManger().setCallBack(callback)


//    第四种
//    val callback2 = object : JavaCallback {
//        override fun show(info: String?) {
//            println(info)
//        }
//    }
//    JavaManger().setCallBack(callback2)


    // 数据bean  解构
    val student = Student("ybx", 10, 'm')
//    println(student.copy())

    val (v1, v2, v3, v4) = student

    println(v1)
    println(v2)
    println(v3)
    println(v4)


    show(true) {
        println("这是高阶函数的方法实现 + $it")
    }


    // 以下是kt中lambada的写法
    // 先是不能调用的   因为没有实现
    val m1: (Int, Int) -> String
//m1()
    val m2: (String) -> Unit


//    现在是能调用的
    val m3 = {}
    m3()

    val m01 = { n1: Int, n2: Int, n3: Int -> println("m01:${n1 + n2 + n3}") }
    m01(1, 2, 3)

    val m02: (String) -> Unit = { println("m02:$it") }
    m02("我是m02")

    val m03: (Int, Int) -> Int = { n1, n2 ->
        val n = n1 + n2
        println("m03:$n")
        n
    }
    println("m03:${m03(4, 7)}")

}

fun String.add(s1: String, s2: String) {
    println(s1 + s2)
}


fun show(boolean: Boolean, loginMethod: (bo: Boolean) -> Unit) {
    loginMethod(boolean)
}