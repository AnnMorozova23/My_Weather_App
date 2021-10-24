package com.example.myweatherapp.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

class UsualService:Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread{

        }.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? = null

    companion object {
        fun start(context: Context) {
            val usualServiceIntent = Intent(context, UsualService::class.java)
            context.startService(usualServiceIntent)
        }

        fun stop(context: Context) {
            val usualServiceIntent = Intent(context, UsualService::class.java)
            context.stopService(usualServiceIntent)
        }
    }


}