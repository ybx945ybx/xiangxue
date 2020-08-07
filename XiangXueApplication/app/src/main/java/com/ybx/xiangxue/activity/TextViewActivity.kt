package com.ybx.xiangxue.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import com.ybx.xiangxue.R
import com.ybx.xiangxue.kotlin.kt.IBasePresenter
import com.ybx.xiangxue.kotlin.kt.MvpBaseActivity
import kotlinx.android.synthetic.main.activity_textview.*

/**
 * @Author 55HAITAO
 * @Date 2020/7/22 11:20 AM
 */
class TextViewActivity : MvpBaseActivity<IBasePresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textview)

        Handler().postDelayed({
            ObjectAnimator.ofFloat(color_change_text_view, "percent", 0f, 1f).setDuration(5000)
                .start()
        }, 2000)
    }
}