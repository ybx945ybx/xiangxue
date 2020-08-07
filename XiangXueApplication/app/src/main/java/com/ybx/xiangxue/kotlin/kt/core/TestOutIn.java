package com.ybx.xiangxue.kotlin.kt.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 55HAITAO
 * @Date 2020/7/6 4:26 PM
 */
class TestOutIn {

    FuClass fuClass = new FuClass();
    ZiClass ziClass = new ZiClass();

    void test01() {

        // 泛型读取模式
        // 只能取  不能改
        List<? extends FuClass> list = new ArrayList<ZiClass>();


//        list.add(fuClass)
        fuClass = list.get(0);
    }

    void test02() {
        List<? super ZiClass> list2 = new ArrayList<FuClass>();

        // 只能改 不能取
        list2.add(ziClass);
//        list2.add(fuClass);

//        ziClass =
//        list2.get(0);
    }
}
