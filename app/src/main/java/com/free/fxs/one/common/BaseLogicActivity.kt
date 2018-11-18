package com.free.fxs.one.common

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.free.fxs.one.application.AppManager

/**
 * @author fxs
 */
abstract class BaseLogicActivity : UiActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            requestPermission()
        }
        AppManager.getAppManager().addActivity(this@BaseLogicActivity)
        Log.e(this@BaseLogicActivity::class.java.simpleName, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.e(this@BaseLogicActivity::class.java.simpleName, "onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.e(this@BaseLogicActivity::class.java.simpleName, "onStart")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.e(this@BaseLogicActivity::class.java.simpleName, "onNewIntent")
    }

    override fun onPause() {
        super.onPause()
        Log.e(this@BaseLogicActivity::class.java.simpleName, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(this@BaseLogicActivity::class.java.simpleName, "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.e(this@BaseLogicActivity::class.java.simpleName, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e(this@BaseLogicActivity::class.java.simpleName, "onRestoreInstanceState")
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        Log.e(this@BaseLogicActivity::class.java.simpleName, "onUserLeaveHint")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.e(this@BaseLogicActivity::class.java.simpleName, "onDestroy")
        AppManager.getAppManager().finishActivity(this@BaseLogicActivity)
    }

    private fun requestPermission() {
        val isAuthorized = ContextCompat.checkSelfPermission(
            this, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
        if (isAuthorized) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100
            )
        } else {
            Log.e("", "permissions accessed")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                if (permissions.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("", "permissions accessed")
                } else {
                    this@BaseLogicActivity.finish()
                }
            }
        }
    }
}

