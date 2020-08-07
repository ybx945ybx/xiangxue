package com.ybx.xiangxue.kotlin.kt.core

/**
 * @Author 55HAITAO
 * @Date 2020/7/6 4:25 PM
 */


fun test() {

    var ziClass: ZiClass = ZiClass()
    var fuClass: FuClass = FuClass()


    // 只取不改
    var list: MutableList<out FuClass> = ArrayList<ZiClass>()


    // 只改不取
    var list2: MutableList<in ZiClass> = ArrayList<FuClass>()
    list2.add(ziClass)
//    fuClass = list2[0]

}


// out ==== ? extent  只能取不能改
// in  ==== ? super   只能修改不能取

class TestOutIn<out T> {

    /*var list: MutableList<FuClass> = ArrayList()

    fun addData(data: T) {
        if (data is FuClass)
            list.add(data)
    }

    fun setData(data: T) {}*/


    fun getData(): T? {
        return null
    }

}


fun <T> test2(data: T) {


}