package com.ybx.xiangxue.kotlin.kt

import androidx.room.Entity

/**
 * @Author 55HAITAO
 * @Date 2020/7/2 2:26 PM
 */
@Entity
  class Student(val name: String, val age: Int, val sex: Char) {

    constructor() : this("",0,'M')
    operator fun component1() = name
    operator fun component2() = age
    operator fun component3() = sex
    operator fun component4() = "我是结构"


}