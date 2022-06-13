package com.pandalibs.fcmlib

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Process
import android.util.Log

object CheckProcess {
    fun isMainProcess(context: Context, packageName: String): Boolean {
        var mainProcess = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (Application.getProcessName().toString() == packageName)
                mainProcess = true
        } else {
            var currentProcName = ""
            val pid = Process.myPid()
            val manager = context.getSystemService(Application.ACTIVITY_SERVICE) as ActivityManager
            for (processInfo in manager.runningAppProcesses) {
                if (processInfo.pid == pid) {
                    currentProcName = processInfo.processName
                    Log.i("MyAppTag", "onCreate: $currentProcName")
                    if (currentProcName == packageName) {
                        mainProcess = true
                        return mainProcess
                    }
                }
            }
        }
        return mainProcess
    }
}