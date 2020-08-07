package com.ybx.xiangxue.kotlin.java;

import androidx.core.text.StringKt;

import com.ybx.xiangxue.kotlin.kt.ClientKt;
import com.ybx.xiangxue.kotlin.kt.KtTestKt;

import java.io.File;

import kotlin.io.FilesKt;

import static kotlin.text.Charsets.UTF_8;

/**
 * @Author 55HAITAO
 * @Date 2020/7/2 11:01 AM
 */
public class JavaClass {
    public String in = "ddddd";

    public static void main(String[] args) {
        KtTestKt.add();

        // java调用扩展函数
//        File file = new File("");
//        FilesKt.readText(file, UTF_8);

        String s = "";
        ClientKt.add( s, "wosi ","  nishi   ");
    }
}
