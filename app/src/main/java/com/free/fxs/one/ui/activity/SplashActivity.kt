package com.free.fxs.one.ui.activity

import android.content.Intent
import android.os.Handler
import com.free.fxs.one.MainActivity
import com.free.fxs.one.common.UiActivity


/**
 * @author fxs
 */
class SplashActivity : UiActivity() {


    override fun getLayoutId(): Int {
        //注意, 这里并没有setContentView,目的是减少首屏渲染
        return -1
    }

    override fun getTitleBarId(): Int {
        return -1
    }

    override fun initView() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)

    }

    override fun initData() {

    }


}
