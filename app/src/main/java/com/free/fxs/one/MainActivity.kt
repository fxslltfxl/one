package com.free.fxs.one

import android.view.KeyEvent
import com.free.fxs.one.common.BaseLogicActivity

class MainActivity : BaseLogicActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main

    }

    override fun getTitleBarId(): Int {
        return R.id.title
    }

    override fun initView() {

    }

    override fun initData() {
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            moveTaskToBack(true)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}