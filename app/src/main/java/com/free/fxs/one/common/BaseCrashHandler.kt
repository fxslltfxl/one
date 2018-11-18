package com.free.fxs.one.common


import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.free.fxs.one.application.App
import com.free.fxs.one.application.AppManager
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class BaseCrashHandler private constructor() : Thread.UncaughtExceptionHandler {
    /**
     * 系统默认异常捕获
     */
    private var mDefaultHandler: Thread.UncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
    private var infoLists: MutableMap<String, String> = HashMap()
    private var thread: Thread
    private var formatter: DateFormat

    init {
        Thread.setDefaultUncaughtExceptionHandler(this)
        thread = Thread({
            Looper.prepare()
            Toast.makeText(App.mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show()
            Looper.loop()
        }, "CatchCrashThread")
        formatter = SimpleDateFormat("yyyy-MM-dd E HH-mm-ss", Locale.CHINA)
    }

    /**
     * 检测到未捕获异常后回调
     * @param thread 线程
     * @param ex 异常
     */
    override fun uncaughtException(thread: Thread, ex: Throwable) {
        if (!handleException(ex)) {
            mDefaultHandler.uncaughtException(thread, ex)
        } else {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                Log.e("", String.format("error : %s", e))
            }
            AppManager.getAppManager().finishAllActivity()
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(0)
        }
    }

    private fun handleException(ex: Throwable?): Boolean {
        if (ex == null) {
            return false
        }
        thread.start()
        //收集设备参数信息
        collectDeviceInfo(App.mContext)
        //保存日志文件
        saveCrashInfo2File(ex)
        return true
    }

    /**
     * 收集设备参数信息
     * @param ctx 上下文
     */
    @Suppress("DEPRECATION")
    private fun collectDeviceInfo(ctx: Context) {
        try {
            val pm = ctx.packageManager
            val pi = pm.getPackageInfo(ctx.packageName, PackageManager.GET_ACTIVITIES)
            if (pi != null) {
                val versionName = if (pi.versionName == null) "null" else pi.versionName
                val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    pi.longVersionCode.toString()
                } else {
                    pi.versionCode.toString()
                }
                infoLists["versionName"] = versionName
                infoLists["versionCode"] = versionCode
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("", "收集package信息时发生错误:$e")
        }

        val fields = Build::class.java.declaredFields
        for (field in fields) {
            try {
                field.isAccessible = true
                infoLists[field.name] = field.get(null).toString()
                Log.d("", "${field.name}:${field.get(null)}")
            } catch (e: Exception) {
                Log.e("", "收集crash信息时发生错误:$e")
            }

        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex 异常
     * @return  返回文件名称,便于将文件传送到服务器
     */
    private fun saveCrashInfo2File(ex: Throwable): String? {
        val sb = StringBuffer()
        for ((key, value) in infoLists) {
            sb.append(key).append("=").append(value).append("\n")
        }

        ex.stackTrace
        val writer = StringWriter()
        val printWriter = PrintWriter(writer)
        ex.printStackTrace(printWriter)
        var cause: Throwable? = ex.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        val result = writer.toString()
        sb.append(result)
        try {
            val timestamp = System.currentTimeMillis()
            val time = SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.CHINA).format(Date())
            val fileName = "crash-$time-$timestamp.log"
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                val path = String.format("%s/logger/", Environment.getExternalStorageDirectory().path)
                val dir = File(path)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                val fos = FileOutputStream(path + fileName)
                Log.e("", sb.toString())
                val bytes = sb.toString().toByteArray(charset("UTF-8"))
                fos.write(bytes)
                fos.close()
            }
            return fileName
        } catch (e: Exception) {
            Log.e("", String.format("an error occured while writing file...%s", e))
        }

        return null
    }

    companion object {
        @Volatile
        private var INSTANCE: BaseCrashHandler? = null

        fun getInstance(): BaseCrashHandler? {
            if (INSTANCE == null) {
                Thread.sleep(100)
                synchronized(BaseCrashHandler::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = BaseCrashHandler()
                    }
                }
            }
            return INSTANCE
        }
    }
}

