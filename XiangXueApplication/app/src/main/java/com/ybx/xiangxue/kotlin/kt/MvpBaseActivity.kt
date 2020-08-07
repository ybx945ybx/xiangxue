package com.ybx.xiangxue.kotlin.kt

import androidx.fragment.app.FragmentActivity
import java.io.Serializable

/**
 * @Author 55HAITAO
 * @Date 2020/7/2 1:37 PM
 */

/// where P : IBasePresenter, P : Serializable    等价于  java   的     P extend: IBasePresenter, Serializable
abstract class MvpBaseActivity<P> : FragmentActivity() where P : IBasePresenter  {

    lateinit var presenter: P
}