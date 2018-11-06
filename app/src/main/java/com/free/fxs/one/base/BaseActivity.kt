package com.free.fxs.one.base

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.free.fxs.one.application.AppManager

/**
 * @author fxs
 */
public abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        AppManager.getAppManager().addActivity(this@BaseActivity)
    }

    override fun onResume() {
        super.onResume()
        Log.e(this@BaseActivity::class.java.simpleName,"onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.e(this@BaseActivity::class.java.simpleName,"onResume")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.e(this@BaseActivity::class.java.simpleName,"onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.e(this@BaseActivity::class.java.simpleName,"onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.getAppManager().finishActivity(this@BaseActivity)
    }
}

