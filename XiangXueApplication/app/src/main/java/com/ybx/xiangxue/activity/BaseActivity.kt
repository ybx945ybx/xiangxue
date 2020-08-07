package com.ybx.xiangxue.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

/**
 * @Author 55HAITAO
 * @Date 2020/8/6 1:41 PM
 */
abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        initVars()
        initView()
        initEvent()
        initData()
    }

    abstract fun setLayoutId(): Int

    open fun initVars() {
    }

    open fun initView() {
    }

    open fun initEvent() {
    }

    open fun initData() {
    }
}