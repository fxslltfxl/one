package com.free.fxs.one.common

import com.free.fxs.baselibrary.base.BaseActivity
import com.gyf.barlibrary.ImmersionBar

abstract class UiActivity : BaseActivity() {
    private var mImmersionBar: ImmersionBar? = null

    override fun init() {
        if (isOpenImmersionBar()) {
            openImmersionBar()
        }
        if (titleBarId > 0) {
            ImmersionBar.setTitleBar(this, findViewById(titleBarId))
        }
        super.init()

    }

    override fun onDestroy() {
        if (mImmersionBar != null) {
            mImmersionBar?.destroy()
        }
        super.onDestroy()
    }

    private fun openImmersionBar() {
        mImmersionBar = ImmersionBar.with(this@UiActivity)
            .statusBarDarkFont(statusBarDarkFont(), 0.2f)
            .transparentNavigationBar()
            .fullScreen(true)
        mImmersionBar?.init()
    }

    open fun isOpenImmersionBar(): Boolean {
        return true
    }

    open fun statusBarDarkFont(): Boolean {
        return true
    }
}