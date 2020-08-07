package com.ybx.xiangxue.activity

import com.ybx.xiangxue.R
import com.ybx.xiangxue.view.FishDrawable
import kotlinx.android.synthetic.main.activity_fish.*

/**
 * @Author 55HAITAO
 * @Date 2020/8/6 2:53 PM
 */
class FishActivity : BaseActivity() {
    override fun setLayoutId() = R.layout.activity_fish

    override fun initView() {
        iv_fish.setImageDrawable(FishDrawable())
    }
}