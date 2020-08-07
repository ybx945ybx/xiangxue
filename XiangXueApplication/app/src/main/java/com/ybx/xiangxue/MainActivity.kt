package com.ybx.xiangxue

import androidx.recyclerview.widget.LinearLayoutManager
import com.ybx.xiangxue.activity.BaseActivity
import com.ybx.xiangxue.adapter.MainRvAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun setLayoutId() = R.layout.activity_main

    override fun initView() {
        rv_main.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainRvAdapter(this@MainActivity, mutableListOf("字体", "流式布局", "灵动的锦鲤"))
        }
    }
}
